package com.todeb.credit_application_project.repository;

import com.todeb.credit_application_project.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT a.id FROM CreditApplication a WHERE a.customer.id = :id")
    long findByCustomerApplicationId(long id);

    boolean existsByIdentityNumber(String identityNumber);

    boolean existsByPhoneNumber(String identityNumber);

}
