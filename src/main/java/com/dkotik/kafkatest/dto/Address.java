package com.dkotik.kafkatest.dto;

public enum Address {

    MDM_1C("1c_mdm"),
    UPP_1C("1c_upp"),
    TERRASOFT("terrasoft");

    private final String topic;

    public String getTopic() {
        return topic;
    }

    Address(String topic) {
        this.topic = topic;
    }
}
