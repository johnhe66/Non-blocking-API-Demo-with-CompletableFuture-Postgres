package com.example.demo.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/future/db")
public class EmployeeFutureDBController {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @GetMapping("/employees")
    public CompletableFuture<List<Employee>> getEmployees() {        
        return employeeRepository.findAllAsyncFuture();
    }

    
}
