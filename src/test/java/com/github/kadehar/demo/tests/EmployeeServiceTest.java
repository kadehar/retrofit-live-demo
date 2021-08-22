package com.github.kadehar.demo.tests;

import com.github.kadehar.demo.client.EmployeeService;
import com.github.kadehar.demo.client.ServiceGenerator;
import com.github.kadehar.demo.model.Employee;
import com.github.kadehar.demo.model.EmployeeData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    private static EmployeeService service;
    private Employee employee;
    private EmployeeData response;

    @BeforeAll
    public static void init() {
        service = ServiceGenerator
                .createService(EmployeeService.class);
    }

    @Test
    public void createNewEmployee() throws IOException {
        step("Create new employee", () -> {
            step("Create request body", () -> {
                employee = new Employee();
                employee.setAge("20");
                employee.setName("Sedrik");
                employee.setSalary("20000");
            });
            step("Call service create method", () -> {
                response = service.create(employee).execute().body();
            });
            step("Check that name is 'Sedrik'", () -> {
                assertEquals(employee.getName(),
                        response.getData().getName());
            });
        });
    }
}
