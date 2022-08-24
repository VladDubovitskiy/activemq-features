package com.epam.dubovitskiy.activemqfeatures.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfig {

    @Value("${broker.activemq.broker-url}")
    private String broker_url;

    @Value("${broker.activemq.user}")
    private String broker_username;

    @Value("${broker.activemq.password}")
    private String broker_password;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        var connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(broker_url);
        connectionFactory.setPassword(broker_username);
        connectionFactory.setUserName(broker_password);
        return connectionFactory;
    }

    @Bean
    @Qualifier("topicJmsTemplate")
    public JmsTemplate jmsTopicTemplate() {
        var template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setPubSubDomain(true);
        return template;
    }

    @Bean
    @Qualifier("queueJmsTemplate")
    public JmsTemplate jmsQueueTemplate() {
        var template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    @Bean(name = "topicListenerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerTopicContainerFactory() {
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean(name = "queueListenerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerQueueContainerFactory() {
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

    @Bean(name = "topicNonDurableListenerFactory")
    public DefaultJmsListenerContainerFactory jmsNonDurableListenerTopicContainerFactory() {
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(true);
        factory.setSubscriptionDurable(false);
        return factory;
    }


    @Bean(name = "topicDurableListenerFactory")
    public DefaultJmsListenerContainerFactory jmsDurableListenerTopicContainerFactory() {
        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(true);
        factory.setSubscriptionDurable(true);
        factory.setClientId("durableTopic");
        return factory;
    }
}
