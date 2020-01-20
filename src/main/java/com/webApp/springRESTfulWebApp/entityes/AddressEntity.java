package com.webApp.springRESTfulWebApp.entityes;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 4888534534534534539L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String addressId;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String streetName;

    @Column(nullable = false, length = 50)
    private String streetNumber;

    @Column(nullable = false, length = 50)
    private String addressType;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "users_id")//,insertable = false, updatable = false
    private UserEntity userDetails;


    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }


    public void setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
    }
}
