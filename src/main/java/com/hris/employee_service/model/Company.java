package com.hris.employee_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.List;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table(name = "company")
@Builder
public class Company implements Serializable {

    private static int next=1;
    public Company () {super();
    //    this.companyId = String.valueOf(next++);
        }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    private String companyName;
    private String information;
    private String contactNumber;

    private String address;
    @DateTimeFormat(pattern = "dd-Mon-yyyy")
    private Date onboardingDate;
    @DateTimeFormat(pattern = "dd-Mon-yyyy")
    private Date offboardingDate;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;
}
