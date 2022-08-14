package com.todeb.credit_application_project.controller;


import com.todeb.credit_application_project.model.DTO.ApplicationDTO;
import com.todeb.credit_application_project.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/application")
public class CreditApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public CreditApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getAll(){
        log.info("Controller: Request to list all applications");
        return new ResponseEntity<>(applicationService.getAll(), HttpStatus.OK);
    }


    @GetMapping("/get-status/{identiyNumber}")
    public ResponseEntity<ApplicationDTO> getStatus(@PathVariable("identiyNumber") String identiyNumber){
        log.info("Controller: Request to fetch application with identity number information");
        return new ResponseEntity<>(applicationService.getStatus(identiyNumber), HttpStatus.OK);
    }


}
