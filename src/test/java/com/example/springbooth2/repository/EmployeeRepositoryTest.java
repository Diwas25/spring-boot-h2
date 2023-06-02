package com.example.springbooth2.repository;

import com.example.springbooth2.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testFindByName_ExistingEmployee_ReturnsEmployee() {
        Employee employee = new Employee("Pete", "Nick");
        entityManager.persistAndFlush(employee);

        Employee foundEmployee = employeeRepository.findByName("Pete");

        Assertions.assertNotNull(foundEmployee);
        Assertions.assertEquals("Pete", foundEmployee.getName());
        Assertions.assertEquals("Nick", foundEmployee.getSupervisor());
    }

    @Test
    public void testFindByName_NonExistingEmployee_ReturnsNull() {
        Employee foundEmployee = employeeRepository.findByName("John");

        Assertions.assertNull(foundEmployee);
    }

    @Test
    public void testSaveEmployee_ValidEmployee_SuccessfullySaved() {
        Employee employee = new Employee("Barbara", "Nick");

        Employee savedEmployee = employeeRepository.save(employee);
        entityManager.flush();
        entityManager.clear();

        Employee retrievedEmployee = entityManager.find(Employee.class, savedEmployee.getId());
        Assertions.assertNotNull(retrievedEmployee);
        Assertions.assertEquals("Barbara", retrievedEmployee.getName());
        Assertions.assertEquals("Nick", retrievedEmployee.getSupervisor());
    }
}

