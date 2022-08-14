package com.todeb.credit_application_project.model.DTO;

import com.todeb.credit_application_project.model.entity.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDTO {
    private long id;
    private int creditLimit;
    private CreditResult creditResult;
    private CustomerDTO customer;
}
