package com.webApp.springRESTfulWebApp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String companyName;

    @OneToOne(mappedBy = "companyEntity", cascade = CascadeType.ALL)
    private CompanyInformationEntity companyInformation;

}
