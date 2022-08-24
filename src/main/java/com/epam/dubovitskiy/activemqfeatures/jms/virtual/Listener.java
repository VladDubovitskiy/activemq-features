package com.epam.dubovitskiy.activemqfeatures.jms.virtual;

import org.apache.activemq.Message;

import javax.jms.JMSException;

public interface Listener {

    void receiveMessageFromTopic_1 (Message jsonMessage) throws JMSException;

    void receiveMessageFromTopic_2 (Message jsonMessage) throws JMSException;

}
