package com.todeb.credit_application_project.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private long id;

    @NotBlank(message = "ID can not be empty or blank")
    private String identityNumber;

    @NotBlank(message = "Name can not be empty or blank")
    private String name;

    @NotBlank(message = "Surname can not be empty or blank")
    private String surname;

    @NotBlank(message = "Phone number can not be empty or blank")
    private String phoneNumber;

    @NotBlank(message = "Salary can not be empty or blank")
    private BigDecimal salary;
}
