package com.epam.dubovitskiy.activemqfeatures.jms.durable.impl;

import com.epam.dubovitskiy.activemqfeatures.jms.durable.Subscriber;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Slf4j
@Component
public class SubscriberImpl implements Subscriber {

    @Value("${topics.topic}")
    private String topic;

    @JmsListener(destination = "${topics.topic}", containerFactory = "topicNonDurableListenerFactory")
    public void receiveMessageByDurableSubscriber(Message jsonMessage) throws JMSException {

        var textMessage = (TextMessage) jsonMessage;
        var text = textMessage.getText();

        log.info("Received message: " + text + " from topic - " + topic + " by the non durable subscriber.");
    }

    @JmsListener(destination = "${topics.topic}", containerFactory = "topicDurableListenerFactory")
    public void receiveMessageByNonDurableSubscriber(Message jsonMessage) throws JMSException {

        var textMessage = (TextMessage) jsonMessage;
        var text = textMessage.getText();

        log.info("Received message: " + text + " from topic - " + topic + " by the durable subscriber.");
    }
}
