package com.epam.dubovitskiy.activemqfeatures.jms.virtual.impl;

import com.epam.dubovitskiy.activemqfeatures.jms.virtual.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerImpl implements Producer {

    @Autowired
    @Qualifier("topicJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Value("${virtual.topic}")
    private String virtualTopic;


    public void sendMessage(String messageText) {
        log.info("Sending message " + messageText + " to virtual topic - " + virtualTopic);
        jmsTemplate.convertAndSend(virtualTopic, messageText);
    }

}