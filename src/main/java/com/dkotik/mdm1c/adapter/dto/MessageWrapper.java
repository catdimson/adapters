package com.dkotik.mdm1c.adapter.dto;

import java.util.Set;

public interface MessageWrapper {

    String getOriginalMessage();

    String getFrom();

    Set<Recipient> getTo();

}
