package com.epam.dubovitskiy.activemqfeatures.jms.sync;

public interface Requestor {

    void sendMessage(String message);
}
