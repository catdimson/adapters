package com.dkotik.kafkatest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Recipient {

    @JsonProperty
    private SystemsAddress address;

    @JsonIgnore
    public SystemsAddress getSystemsAddress() {
        return address;
    }

    public void setSystemsAddress(SystemsAddress addressee) {
        this.address = addressee;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "address=" + address +
                '}';
    }
}
