package com.epam.dubovitskiy.activemqfeatures.jms.durable.impl;

import com.epam.dubovitskiy.activemqfeatures.jms.durable.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PublisherImpl implements Publisher {

    @Autowired
    @Qualifier("topicJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Value("${topics.topic}")
    private String topic;

    @Override
    public void sendMessage(String message) {
        log.info("Sending message " + message + " to  topic - " + topic);

        jmsTemplate.convertAndSend(topic, message);
    }
}