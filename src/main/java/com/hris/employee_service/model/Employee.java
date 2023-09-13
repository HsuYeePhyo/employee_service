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
    private String id;
    @NotNull
    private String fullname;
//    @NotNull
    @Column(name = "company_id")
    private String companyId;


    private String department_id;
    @Size(max = 50)
    @Email
    @NotNull
    private String email;
    @Size(max = 200)
    private String address;
    private String contactnumber;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dob;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startdate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date enddate;
    private String jobgrade_id;
    private String bankaccount;
    private Double salary;
    private String user_id;


}