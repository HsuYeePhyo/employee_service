package com.hris.employee_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "emp_role")
public class EmployeeRole implements Serializable {
    @Id
    @GenericGenerator(name = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EmpRole name;

    public EmployeeRole() {super();}
    public EmployeeRole(EmpRole role){
        this.name = role;
    }
}
