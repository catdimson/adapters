package com.dkotik.mdm1c.adapter.services;

import com.dkotik.mdm1c.adapter.dto.MessageWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface Producer {

    void sendMessage(MessageWrapper messageWrapper) throws JsonProcessingException;

}
