package com.dkotik.kafkatest.services.kafka;

@FunctionalInterface
public interface Func<T> {
    T square(T num);
}
