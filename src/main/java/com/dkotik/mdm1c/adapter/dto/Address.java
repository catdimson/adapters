package com.dkotik.mdm1c.adapter.dto;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"topic"}, name = "address")
public enum Address {

    MDM_1C("MDM_1C"),
    UPP_1C("UPP_1C"),
    TERRASOFT("TERRASOFT");

    private final String topic;

    public String getTopic() {
        return topic;
    }

    Address(String topic) {
        this.topic = topic;
    }
}
