package com.webApp.springRESTfulWebApp.repositories;

import com.webApp.springRESTfulWebApp.entities.CompanyInformationEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompanyInformationRepository extends CrudRepository<CompanyInformationEntity,Long> {
}
