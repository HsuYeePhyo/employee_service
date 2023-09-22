package com.hris.employee_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@Table(name = "emp_role")
public class EmployeeRole implements Serializable {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;
    @Enumerated(EnumType.STRING)
    private EmpRole name;
    public EmployeeRole() { super();}
    public EmployeeRole(EmpRole role){
        this.name = role;
    }
}
