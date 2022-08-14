package com.todeb.credit_application_project.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @PostMapping
    public String auth(){
        log.info("Controller: Request to user login");
        return "You are authenticated";
    }
}
