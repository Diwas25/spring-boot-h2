package com.example.springbooth2.service;

import com.example.springbooth2.model.Employee;
import com.example.springbooth2.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeHierarchyServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeHierarchyService employeeHierarchyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveEmployeeHierarchy() {
        Map<String, String> hierarchy = new HashMap<>();
        hierarchy.put("Pete", "Nick");
        hierarchy.put("Barbara", "Nick");

        employeeHierarchyService.saveEmployeeHierarchy(hierarchy);

        verify(employeeRepository, times(2)).save(any(Employee.class));
    }

    @Test
    public void testGetSupervisorHierarchy() {
        String employeeName = "Pete";
        String supervisorName = "Nick";
        String supervisorSupervisorName = "Sophie";

        Employee employee = new Employee(employeeName, supervisorName);
        Employee supervisor = new Employee(supervisorName, supervisorSupervisorName);
        Employee supervisorSupervisor = new Employee(supervisorSupervisorName, null);

        when(employeeRepository.findByName(employeeName)).thenReturn(employee);
        when(employeeRepository.findByName(supervisorName)).thenReturn(supervisor);
        when(employeeRepository.findByName(supervisorSupervisorName)).thenReturn(supervisorSupervisor);

        String result = employeeHierarchyService.getSupervisorHierarchy(employeeName);

        assertEquals("Supervisor: " + supervisorName + ", Supervisor's Supervisor: " + supervisorSupervisorName, result);
    }

    @Test
    public void testGetSupervisorHierarchy_employeeNotFound() {
        String employeeName = "John";

        when(employeeRepository.findByName(employeeName)).thenReturn(null);

        String result = employeeHierarchyService.getSupervisorHierarchy(employeeName);

        assertEquals("Employee not found", result);
    }
}

