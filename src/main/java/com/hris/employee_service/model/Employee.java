package com.hris.employee_service.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
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
    private String companyId;

    @ManyToOne
   // @JoinColumn(name ="departmentId", referencedColumnName = "departmentId")
    private Department department;
    @ManyToOne
    private Company company;
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
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}