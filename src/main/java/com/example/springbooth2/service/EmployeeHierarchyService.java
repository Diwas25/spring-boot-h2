package com.example.springbooth2.service;

import com.example.springbooth2.model.Employee;
import com.example.springbooth2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmployeeHierarchyService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeHierarchyService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void saveEmployeeHierarchy(Map<String, String> hierarchy) {
        for (Map.Entry<String, String> entry : hierarchy.entrySet()) {
            Employee employee = new Employee(entry.getKey(), entry.getValue());
            employeeRepository.save(employee);
        }
    }

    public String getSupervisorHierarchy(String employeeName) {
        Employee employee = employeeRepository.findByName(employeeName);
        if (employee != null) {
            Employee supervisor = employeeRepository.findByName(employee.getSupervisor());
            if (supervisor != null) {
                Employee supervisorSupervisor = employeeRepository.findByName(supervisor.getSupervisor());
                return "Supervisor: " + supervisor.getName() + ", Supervisor's Supervisor: " + supervisorSupervisor.getName();
            }
        }
        return "Employee such not found";
    }
}

