package com.dkotik.kafkatest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Recipient {

    @JsonProperty
    private Address address;

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
