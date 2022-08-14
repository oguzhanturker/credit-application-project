package com.todeb.credit_application_project.model.mapper;

import com.todeb.credit_application_project.model.DTO.CustomerDTO;
import com.todeb.credit_application_project.model.entity.Customer;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Named("CustomerDtoToCustomer")
    Customer CustomerDtoToCustomer(CustomerDTO customerDto);

    @Named("CustomerToCustomerDto")
    CustomerDTO CustomerToCustomerDto(Customer customer);

    @IterableMapping(qualifiedByName = "CustomerToCustomerDto")
    List<CustomerDTO> CustomersToCustomerDto(List<Customer> customers);
}
