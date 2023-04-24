package com.example.demo.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;


import java.util.List;

@RestController
@RequestMapping("/api/normal/db")
public class EmployeeNormalDBController {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
                return employeeRepository.findAll();
    }
    
}
