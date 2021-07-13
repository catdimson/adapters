package com.dkotik.mdm1c.adapter.services;

import com.dkotik.mdm1c.adapter.dto.MessageWrapper;

public interface Producer {

    void sendMessage(MessageWrapper messageWrapper);

}
