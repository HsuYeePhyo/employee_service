package com.hris.employee_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
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
        }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    private String companyName;
    private String information;
    private String contactNumber;

    private String address;
    @DateTimeFormat(pattern = "dd-Mon-yyyy")
    private Date onBoardingDate;
    @DateTimeFormat(pattern = "dd-Mon-yyyy")
    private Date offBoardingDate;
}
