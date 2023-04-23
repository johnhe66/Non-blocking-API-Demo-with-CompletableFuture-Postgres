package com.example.demo.entity;

import lombok.Data;

@Data
public class Employee {
    private int id;
    private String name;
    private String position;

    public Employee(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    // Getters, setters, and toString
}
