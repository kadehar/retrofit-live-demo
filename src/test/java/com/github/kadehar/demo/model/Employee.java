package com.github.kadehar.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Employee {
    private Long id;
    private String name;
    private String salary;
    private String age;
}
