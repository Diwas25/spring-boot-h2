package com.example.springbooth2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String supervisor;

    public Employee() {
    }

    public Employee(String name, String supervisor) {
        this.name = name;
        this.supervisor = supervisor;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public String getSupervisor() {
        return supervisor;
    }

}

