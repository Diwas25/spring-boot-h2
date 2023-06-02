package com.example.springbooth2.controller;

import com.example.springbooth2.service.EmployeeHierarchyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeHierarchyController {

    private final EmployeeHierarchyService employeeHierarchyService;

    @Autowired
    public EmployeeHierarchyController(EmployeeHierarchyService employeeHierarchyService) {
        this.employeeHierarchyService = employeeHierarchyService;
    }

    @PostMapping("/employees")
    public void postEmployeeHierarchy(@RequestBody Map<String, String> hierarchy) {
        employeeHierarchyService.saveEmployeeHierarchy(hierarchy);
    }

    @GetMapping("/supervisor/{employeeName}")
    public String getSupervisorAndSupervisorSupervisor(@PathVariable String employeeName) {
        return employeeHierarchyService.getSupervisorHierarchy(employeeName);
    }

    @GetMapping("/getMsg")
    public String getMsg(){
        return "Spring Security & H2 DB Example";
    }
}

