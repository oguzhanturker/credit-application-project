package com.todeb.credit_application_project.controller;


import com.todeb.credit_application_project.model.DTO.CustomerDTO;
import com.todeb.credit_application_project.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> save(@RequestBody @Valid CustomerDTO customerDto){
        log.info("Controller: Request to save customer with object information");
        return new ResponseEntity<>(customerService.save(customerDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll(){
        log.info("Controller: Request to list all customers");
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDTO> get(@PathVariable("id") long id){
        log.info("Controller: Request to fetch customer with id information");
        return new ResponseEntity<>(customerService.get(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@RequestBody @Valid CustomerDTO customerDto, @PathVariable("id") long id){
        customerDto.setId(id);
        log.info("Controller: Request to update customer with object information");
        return new ResponseEntity<>(customerService.update(customerDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        log.info("Controller: Request to delete customer with id information");
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
