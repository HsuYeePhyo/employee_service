package com.hris.employee_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Entity
@Table(name = "department")
@Builder
public class Department implements Serializable {
    public Department () { super();}
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String departmentId;
    private String departmentName;
    private String departmentDesc;
}
