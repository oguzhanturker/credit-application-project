package com.todeb.credit_application_project.repository;


import com.todeb.credit_application_project.model.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<CreditApplication, Long> {
    CreditApplication findByCustomerIdentityNumber(String identityNumber);
}
