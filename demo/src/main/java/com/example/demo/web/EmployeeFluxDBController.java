package com.example.demo.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;

import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/flux/db")
public class EmployeeFluxDBController {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @GetMapping("/employees")
    public Flux<List<Employee>> getEmployees() {
                return employeeRepository.findAllAsyncFlux();
    }
    
}
