package com.epam.dubovitskiy.activemqfeatures.jms.sync.impl;

import com.epam.dubovitskiy.activemqfeatures.jms.sync.Requestor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RequestorImpl implements Requestor {

    @Autowired
    @Qualifier("queueJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Value("${sync-communication.request-queue}")
    private String requestQueue;

    @Value("${sync-communication.reply-queue}")
    private String replyQueue;

    public void sendMessage(String message) {
        log.info("Sending request: " + requestQueue);

        jmsTemplate.convertAndSend(requestQueue, message);
        var replyObject = jmsTemplate.receiveAndConvert(replyQueue);

        log.info("Reply: " + replyObject);
    }
}
