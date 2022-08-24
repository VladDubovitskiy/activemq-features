package com.epam.dubovitskiy.activemqfeatures.jms.virtual.impl;

import com.epam.dubovitskiy.activemqfeatures.jms.virtual.Listener;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Slf4j
@Component
public class ListenerImpl implements Listener {

    @Value("${virtual.topic}")
    private String virtualTopic;


    @JmsListener(destination = "Consumer.consumer1." + "${virtual.topic}", containerFactory = "queueListenerFactory")
    public void receiveMessageFromTopic_1(Message jsonMessage) throws JMSException {

        var textMessage = (TextMessage) jsonMessage;
        var messageData = textMessage.getText();
        log.info("Received message: " + messageData + " from virtual topic - " + virtualTopic + " by the first consumer");
    }

    @JmsListener(destination = "Consumer.consumer2." + "${virtual.topic}", containerFactory = "queueListenerFactory")
    public void receiveMessageFromTopic_2(Message jsonMessage) throws JMSException {

        var textMessage = (TextMessage) jsonMessage;
        var messageData = textMessage.getText();
        log.info("Received message: " + messageData + " from virtual topic - " + virtualTopic + " by the second consumer");
    }

    @JmsListener(destination = "Consumer.consumer3." + "${virtual.topic}", containerFactory = "queueListenerFactory")
    public void receiveMessageFromTopic_3(Message jsonMessage) throws JMSException {

        var textMessage = (TextMessage) jsonMessage;
        var messageData = textMessage.getText();
        log.info("Received message: " + messageData + " from virtual topic - " + virtualTopic + " by the third consumer");
    }

    @JmsListener(destination = "Consumer.consumer4." + "${virtual.topic}", containerFactory = "queueListenerFactory")
    public void receiveMessageFromTopic_4(Message jsonMessage) throws JMSException {

        var textMessage = (TextMessage) jsonMessage;
        var messageData = textMessage.getText();
        log.info("Received message: " + messageData + " from virtual topic - " + virtualTopic + " by the fourth consumer");
    }

}
