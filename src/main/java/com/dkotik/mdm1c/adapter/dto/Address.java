package com.dkotik.mdm1c.adapter.dto;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.*;

@XmlEnum
public enum Address {

    @XmlEnumValue("MDM_1C") MDM_1C("MDM_1C"),
    @XmlEnumValue("UPP_1C") UPP_1C("UPP_1C"),
    @XmlEnumValue("TERRASOFT") TERRASOFT("TERRASOFT");

    private final String topic;

    public String getTopic() {
        return topic;
    }

    Address(String topic) {
        this.topic = topic;
    }
}
