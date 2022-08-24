package com.epam.dubovitskiy.activemqfeatures.jms.sync;

import org.apache.activemq.Message;

import javax.jms.JMSException;

public interface Replier {

    String processRequest(Message message) throws JMSException;
}
