package com.todeb.credit_application_project.model.mapper;

import com.todeb.credit_application_project.model.DTO.ApplicationDTO;
import com.todeb.credit_application_project.model.entity.CreditApplication;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Named("ApplicationDtoToApplicaion")
    CreditApplication ApplicationDTOToApplication(ApplicationDTO applicationDTO);

    @Named("ApplicationToApplicaionDto")
    ApplicationDTO ApplicationToApplicationDTO(CreditApplication creditApplication);

    @IterableMapping(qualifiedByName = "ApplicationToApplicationDto")
    List<ApplicationDTO> ApplicationsToApplicationDTO(List<CreditApplication> appplications);
}
