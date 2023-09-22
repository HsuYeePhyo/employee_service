package com.hris.employee_service.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
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
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
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
    private Set<EmployeeRole> empRoles;
}