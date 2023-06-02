//package com.example.springbooth2.service;
//
//import com.example.springbooth2.model.Employee;
//import com.example.springbooth2.repository.EmployeeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.mockito.Mockito.*;
//
//public class EmployeeHierarchyServiceTest {
//
//    private EmployeeHierarchyService employeeHierarchyService;
//
//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        employeeHierarchyService = new EmployeeHierarchyService(employeeRepository);
//    }
//
//    @Test
//    public void testSaveEmployeeHierarchy() {
//        Map<String, String> hierarchy = new HashMap<>();
//        hierarchy.put("Pete", "Nick");
//        hierarchy.put("Barbara", "Nick");
//
//        Employee pete = new Employee();
//        pete.setName("Pete");
//        Employee nick = new Employee();
//        nick.setName("Nick");
//
//        when(employeeRepository.findByName("Pete")).thenReturn(null);
//        when(employeeRepository.findByName("Nick")).thenReturn(nick);
//
//        employeeHierarchyService.saveEmployeeHierarchy(hierarchy);
//
//        verify(employeeRepository, times(1)).save(pete);
//        verify(employeeRepository, times(1)).save(nick);
//        verify(nick, times(1)).setSupervisor(nick);
//    }
//
//    @Test
//    public void testGetSupervisorHierarchy() {
//        String employeeName = "Pete";
//
//        Employee pete = new Employee();
//        pete.setName("Pete");
//        Employee nick = new Employee();
//        nick.setName("Nick");
//        pete.setSupervisor(nick);
//
//        when(employeeRepository.findByName(employeeName)).thenReturn(pete);
//
//        String hierarchy = employeeHierarchyService.getSupervisorHierarchy(employeeName);
//
//        assertEquals("Nick -> ", hierarchy);
//    }
//}
//
