package com.webApp.springRESTfulWebApp;

import com.webApp.springRESTfulWebApp.entities.CompanyEntity;
import com.webApp.springRESTfulWebApp.entities.CompanyInformationEntity;
import com.webApp.springRESTfulWebApp.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class LoadingTestData {

    @Autowired
    private CompanyRepository companyRepository;



    @Transactional
    @EventListener
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyName("BP");
        CompanyInformationEntity companyInformationEntity =new CompanyInformationEntity();
        companyInformationEntity.setAdditionalInfo("British Petroleum, OIL & GAS");
        companyInformationEntity.setCompanyEntity(companyEntity);
        companyEntity.setCompanyInformation(companyInformationEntity);
        companyRepository.save(companyEntity);

    }
}
