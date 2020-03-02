package com.webApp.springRESTfulWebApp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CompanyInformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String additionalInfo;

    @OneToOne
    @JoinColumn(name = "companyEntity")
    private CompanyEntity companyEntity;

}
