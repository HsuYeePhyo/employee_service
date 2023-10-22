package com.hris.employee_service.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
@Data
@AllArgsConstructor
@Entity
@Table(name = "employee",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Builder
public class Employee implements Serializable  {
    public Employee (){super();}
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String employeeId;
    private String fullName;
    private Long companyId;
    @ManyToOne
    private Department department;
    @Size(max = 50)
    @Email
    @NotNull
    private String email;
    @Size(max = 200)
    private String address;
    private String contactNumber;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dob;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endDate;
    private String jobGradeId;
    private String bankAccount;
    private Double salary;
    private String userId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),//column for Employee
            inverseJoinColumns = @JoinColumn(name = "role_id") // column for EmployeeRole
    )
    private Set<EmployeeRole> empRoles = new HashSet<>();
    public Employee (String employeeId, String fullName) {
        this.employeeId = employeeId;
        this.fullName = fullName;
    }
    public Employee (String employeeId, String fullName, Long companyId) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.companyId = companyId;
    }
    public Employee (String employeeId, String fullName, String email) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
    }
}