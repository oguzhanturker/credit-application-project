package com.todeb.credit_application_project.model.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id",length = 11,updatable = false,nullable = false)
    @NotBlank(message = "ID can not be empty or blank")
    private String identityNumber;

    @NotBlank(message = "Name can not be empty or blank")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Surname can not be empty or blank")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "ID no can not be empty or blank")
    @Column(name = "national_id")
    private String nationalID;

    @NotBlank(message = "Phone number can not be empty or blank")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Salary can not be empty or blank")
    @Column(name = "salary")
    private int salary;

    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private CreditApplication creditApplication;



}
