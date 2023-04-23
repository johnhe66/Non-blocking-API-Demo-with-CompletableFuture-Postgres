package com.example.demo.repo;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
public class EmployeeRepository {
    
    private final DataSource dataSource;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    public EmployeeRepository( DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CompletableFuture<List<Employee>> findAllAsyncFuture() {
        return CompletableFuture.supplyAsync(this::findAll, executorService);
    }

    public Flux<List<Employee>> findAllAsyncFlux() {
        return Mono.fromCallable(this::findAll)
                .subscribeOn(Schedulers.boundedElastic())
                .flux();
    }

    private List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Employee");
             ResultSet resultSet = stmt.executeQuery()) {

            // block thread for a few seconds
            Thread.sleep(3000);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                employees.add(new Employee(id, name, position));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching employees", e);
        }
        return employees;
    }
}
