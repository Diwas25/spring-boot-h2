package com.example.springbooth2.controller;

import com.example.springbooth2.service.EmployeeHierarchyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeHierarchyController.class)
public class EmployeeHierarchyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeHierarchyService employeeHierarchyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPostEmployeeHierarchy() throws Exception {
        doNothing().when(employeeHierarchyService).saveEmployeeHierarchy(anyMap());

        Map<String, String> hierarchy = new HashMap<>();
        hierarchy.put("Pete", "Nick");
        hierarchy.put("Barbara", "Nick");

        mockMvc.perform(post("/api/employees")
                        .header("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ=")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hierarchy)))
                .andExpect(status().isOk());

        verify(employeeHierarchyService, times(1)).saveEmployeeHierarchy(hierarchy);
    }

    @Test
    public void testGetSupervisorAndSupervisorSupervisor() throws Exception {
        when(employeeHierarchyService.getSupervisorHierarchy(anyString())).thenReturn("Supervisor");

        mockMvc.perform(get("/api/supervisor/{employeeName}", "John")
                        .header("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
                .andExpect(status().isOk())
                .andExpect(content().string("Supervisor"));

        verify(employeeHierarchyService, times(1)).getSupervisorHierarchy("John");
    }

    @Test
    public void testGetMsg() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/getMsg")
                        .header("Authorization", "Basic dXNlcm5hbWU6cGFzc3dvcmQ="))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Spring Security & H2 DB Example"));
    }
}
