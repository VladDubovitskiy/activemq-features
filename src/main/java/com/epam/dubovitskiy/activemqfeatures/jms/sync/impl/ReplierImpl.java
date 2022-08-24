package com.epam.dubovitskiy.activemqfeatures.jms.sync.impl;

import com.epam.dubovitskiy.activemqfeatures.jms.sync.Replier;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.Message;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ReplierImpl implements Replier {

    @Autowired
    @Qualifier("queueJmsTemplate")
    private JmsTemplate jmsTemplate;


    @Override
    @JmsListener(destination = "${sync-communication.request-queue}", containerFactory = "queueListenerFactory")
    @SendTo("${sync-communication.reply-queue}")
    public String processRequest(Message message) throws JMSException {

        var activeMQTextMessage = (ActiveMQTextMessage) message;
        try {
            TimeUnit.SECONDS.sleep(5L);
            log.info("Received message from request channel: " + activeMQTextMessage.getText());
            return "Success";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
