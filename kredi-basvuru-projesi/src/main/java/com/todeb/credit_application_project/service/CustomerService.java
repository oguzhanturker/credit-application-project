package com.todeb.credit_application_project.service;

import com.todeb.credit_application_project.model.DTO.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO save(CustomerDTO customerDto);
    CustomerDTO update(CustomerDTO customerDto);
    void delete(long id);
    List<CustomerDTO> getAll();
    CustomerDTO get(long id);
}
