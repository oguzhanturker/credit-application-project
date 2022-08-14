package com.todeb.credit_application_project.service.impl;

import com.todeb.credit_application_project.exceptions.ResourceNotFoundException;
import com.todeb.credit_application_project.model.DTO.ApplicationDTO;
import com.todeb.credit_application_project.model.entity.CreditApplication;
import com.todeb.credit_application_project.model.entity.CreditResult;
import com.todeb.credit_application_project.model.entity.Customer;
import com.todeb.credit_application_project.model.mapper.ApplicationMapper;
import com.todeb.credit_application_project.repository.ApplicationRepository;
import com.todeb.credit_application_project.service.ApplicationService;
import com.todeb.credit_application_project.util.notificationService.NotificationService;
import com.todeb.credit_application_project.util.rules.ApplicationRule;
import com.todeb.credit_application_project.util.scoreService.ScoreService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.javatuples.Pair;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final ScoreService scoreService;
    private final NotificationService notificationService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper,
                                  ScoreService scoreService, NotificationService notificationService) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.scoreService = scoreService;
        this.notificationService = notificationService;
    }

    @Override
    public ApplicationDTO makeApplication(Customer customer) {
        Pair<BigDecimal, CreditResult> result = applicationResult(scoreService.getScore(customer.getIdentityNumber()), BigDecimal.valueOf(customer.getSalary()));
        CreditApplication application = CreditApplication.builder().customer(customer).creditLimit(String.valueOf(result.getValue0())).creditResult(result.getValue1()).build();
        Boolean status = (application.getCreditResult() == CreditResult.CONFIRMED);
        notificationService.sendSms(customer.getPhoneNumber(), status);
        log.info("Service: Sms sending has been completed.");
        return applicationMapper.ApplicationToApplicationDTO(applicationRepository.save(application));
    }

    @Override
    public ApplicationDTO update(Customer customer, long applicationId) {
        Pair<BigDecimal, CreditResult> result = applicationResult(scoreService.getScore(customer.getIdentityNumber()), BigDecimal.valueOf(customer.getSalary()));
        CreditApplication application = CreditApplication.builder().customer(customer).creditLimit(String.valueOf(result.getValue0())).creditResult(result.getValue1()).build();
        Boolean status = (application.getCreditResult() == CreditResult.CONFIRMED);
        application.setId(applicationId);
        notificationService.sendSms(customer.getPhoneNumber(), status);
        log.info("Service: Sms sending has been completed.");
        return applicationMapper.ApplicationToApplicationDTO(applicationRepository.save(application));
    }

    @Override
    public List<ApplicationDTO> getAll() {
        List<CreditApplication> applications = applicationRepository.findAll();
        return applicationMapper.ApplicationsToApplicationDTO(applications);
    }

    @Override
    public ApplicationDTO getStatus(String identityNumber) {
        CreditApplication application = applicationRepository.findByCustomerIdentityNumber(identityNumber);
        if(Objects.isNull(application)){
            throw new ResourceNotFoundException("Application with identity number: " + identityNumber + " not found.");
        }
        return applicationMapper.ApplicationToApplicationDTO(application);
    }

    @Override
    public Pair<BigDecimal, CreditResult> applicationResult(int score, BigDecimal salary) {
        if(score < 500){
            return new Pair<>(BigDecimal.valueOf(0), CreditResult.UNCONFIRMED);
        }else if(score >= 500 && score < 1000 && salary.intValue() <= 5000){
            return new Pair<>(BigDecimal.valueOf(10000), CreditResult.CONFIRMED);
        }else if(score >= 500 && score < 1000 && salary.intValue() > 5000){
            return new Pair<>(BigDecimal.valueOf(20000), CreditResult.CONFIRMED);
        }else if(score >= 1000){
            return new Pair<>((salary.multiply(BigDecimal.valueOf(ApplicationRule.CREDIT_LIMIT_MULTIPLIER))), CreditResult.CONFIRMED);
        }
        return new Pair<>(BigDecimal.valueOf(0), CreditResult.UNCONFIRMED);
    }
}
