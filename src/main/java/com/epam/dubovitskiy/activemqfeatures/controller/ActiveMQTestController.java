package com.epam.dubovitskiy.activemqfeatures.controller;

import com.epam.dubovitskiy.activemqfeatures.jms.durable.Publisher;
import com.epam.dubovitskiy.activemqfeatures.jms.sync.Requestor;
import com.epam.dubovitskiy.activemqfeatures.jms.virtual.Producer;
import com.epam.dubovitskiy.activemqfeatures.rq.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activemq")
@RequiredArgsConstructor
@Slf4j
public class ActiveMQTestController {

    private final Publisher publisher;
    private final Requestor requestor;
    private final Producer producer;

    @PostMapping("topic")
    public void sendMessageToTopic(@RequestBody Message message) {
        publisher.sendMessage(message.toString());
    }

    @PostMapping("sync")
    public void sendToSyncRequestor(@RequestBody Message message) {
        log.info("-------------START SYNC-------------");
        requestor.sendMessage(message.toString());
        log.info("-------------END SYNC-------------");
    }

    @PostMapping("virtual")
    public void sendToProducer(@RequestBody Message message) {
        log.info("-------------START VIRTUAL-------------");
        producer.sendMessage(message.toString());
        log.info("-------------END VIRTUAL-------------");
    }
}
