package com.dkotik.kafkatest.models;

import java.time.LocalDate;

public class Message {

    private String message;
    private Integer age;

    public Message() {
    }

    public Message(String message, Integer age) {
        this.message = message;
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", age=" + age +
                '}';
    }
}
