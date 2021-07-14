package com.dkotik.mdm1c.adapter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class Recipient {

    @JsonProperty
    @XmlElement(name = "address")
    private Address address;

    @XmlTransient
    @JsonIgnore
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "address=" + address +
                '}';
    }
}
