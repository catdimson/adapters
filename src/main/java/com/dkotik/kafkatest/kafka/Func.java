package com.dkotik.kafkatest.kafka;

@FunctionalInterface
public interface Func<T> {
    T square(T num);
}
