package com.todeb.credit_application_project.service.impl;


import com.todeb.credit_application_project.exceptions.IdentityNumberIsAlreadyExistException;
import com.todeb.credit_application_project.exceptions.PhoneNumberIsAlreadyExistException;
import com.todeb.credit_application_project.exceptions.ResourceNotFoundException;
import com.todeb.credit_application_project.model.DTO.CustomerDTO;
import com.todeb.credit_application_project.model.entity.Customer;
import com.todeb.credit_application_project.model.mapper.CustomerMapper;
import com.todeb.credit_application_project.repository.CustomerRepository;
import com.todeb.credit_application_project.service.ApplicationService;
import com.todeb.credit_application_project.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ApplicationService applicationService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, ApplicationService applicationService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.applicationService = applicationService;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDto) {
        if(customerRepository.existsByIdentityNumber(customerDto.getIdentityNumber())){
            throw new IdentityNumberIsAlreadyExistException("Identity Number with " + customerDto.getIdentityNumber() + " is already exist.");
        }
        if(customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber())){
            throw new PhoneNumberIsAlreadyExistException("Phone Number with " + customerDto.getPhoneNumber() + " is already exist.");
        }
        Customer customer = customerMapper.CustomerDtoToCustomer(customerDto);
        Customer saveCustomer = customerRepository.save(customer);
        log.info("Service: Application has been created.");
        applicationService.makeApplication(customer);
        log.info("Service: Sms sending has been completed.");
        return customerMapper.CustomerToCustomerDto(saveCustomer);
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDto) {
        Customer controlCustomer = customerRepository.findById(customerDto.getId()).get();
        if(!controlCustomer.getIdentityNumber().equals(customerDto.getIdentityNumber())){
            if(customerRepository.existsByIdentityNumber(customerDto.getIdentityNumber())){
                throw new IdentityNumberIsAlreadyExistException("Identity Number with " + customerDto.getIdentityNumber() + " is already exist.");
            }
        }
        if(!controlCustomer.getPhoneNumber().equals(customerDto.getPhoneNumber())) {
            if (customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber())) {
                throw new PhoneNumberIsAlreadyExistException("Phone Number with " + customerDto.getPhoneNumber() + " is already exist.");
            }
        }
        Customer customer = customerMapper.CustomerDtoToCustomer(customerDto);
        Customer updateCustomer = customerRepository.save(customer);
        applicationService.update(customer, customerRepository.findByCustomerApplicationId(customer.getId()));
        log.info("Service: Application update handle has been completed");
        return customerMapper.CustomerToCustomerDto(updateCustomer);
    }

    @Override
    public void delete(long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.CustomersToCustomerDto(customers);
    }

    @Override
    public CustomerDTO get(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Customer with id: %d not found.", id)));
        return customerMapper.CustomerToCustomerDto(customer);
    }
}
