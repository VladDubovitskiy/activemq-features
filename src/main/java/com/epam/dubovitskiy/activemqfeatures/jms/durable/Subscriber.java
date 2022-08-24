package com.epam.dubovitskiy.activemqfeatures.jms.durable;

import org.apache.activemq.Message;

import javax.jms.JMSException;

public interface Subscriber {

    void receiveMessageByDurableSubscriber(Message jsonMessage) throws JMSException;

    void receiveMessageByNonDurableSubscriber(Message jsonMessage) throws JMSException;

}
