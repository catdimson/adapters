package com.dkotik.mdm1c.adapter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.common.protocol.types.Field;

import javax.xml.bind.annotation.*;
import java.util.Objects;

public class Recipient {

    @JsonProperty
    @XmlElement(name = "address")
    private Address address;

    @XmlElement
    private String otherField;

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
