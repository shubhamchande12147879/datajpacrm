package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int custId;

    @Size(min = 2,message = "Customer Name must valid")
    private String custName;

    private double custBalance;

    private String custAddress;
    @Range(min = 1000000000l,max = 99999999999l,message = "Customer conact numbe is must be valid")
    private long custContact;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date custDOB;

    @Email(message = "customer emailid must be valid")
    private String custEmailId;

    @Size(min = 4,message = "password should have atleast 4 characer")
    private String custPassword;

}

