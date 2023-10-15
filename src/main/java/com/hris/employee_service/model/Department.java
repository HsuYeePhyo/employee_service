package com.hris.employee_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
@Entity
@Table(name = "department")
@Builder
public class Department implements Serializable {
    public Department () { super();}
    public Department(String id, String name){
        departmentId = id;
        departmentName = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String departmentId;
    private String departmentName;
    private String departmentDesc;
    private Long companyId;
}
