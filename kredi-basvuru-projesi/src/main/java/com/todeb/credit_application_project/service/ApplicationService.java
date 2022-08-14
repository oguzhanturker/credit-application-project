package com.todeb.credit_application_project.service;

import com.todeb.credit_application_project.model.DTO.ApplicationDTO;
import com.todeb.credit_application_project.model.entity.CreditResult;
import com.todeb.credit_application_project.model.entity.Customer;

import java.math.BigDecimal;
import java.util.List;

import org.javatuples.Pair;

public interface ApplicationService {

    ApplicationDTO makeApplication(Customer customer);
    ApplicationDTO update(Customer customer, long applicationId);
    List<ApplicationDTO> getAll();
    ApplicationDTO getStatus(String identityNumber);
    Pair<BigDecimal, CreditResult> applicationResult(int score, BigDecimal salary);
}
