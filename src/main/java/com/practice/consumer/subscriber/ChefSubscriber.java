package com.practice.consumer.subscriber;

import javax.jms.JMSException;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChefSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChefSubscriber.class);

    @JmsListener(destination = "${activemq.destination.queue.chef}", containerFactory = "queueConnectionFactory")
    public void processQueue(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("ChefSubscriber > Message received from chef queue " + o.getText());
    }

    @JmsListener(destination = "${activemq.destination.topic.chef}", subscription = "chef_topic", containerFactory = "topicConnectionFactory")
    public void processTopic(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("ChefSubscriber > Message received from chef topic " + o.getText());
    }

    @JmsListener(destination = "${activemq.destination.topic.restaurant}", subscription = "restaurant_topic", containerFactory = "topicConnectionFactory")
    public void processRestaurantTopic(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("ChefSubscriber > Message received from restaurant topic " + o.getText());
    }

    @JmsListener(destination = "${activemq.destination.topic.hello}", subscription = "hello_topic", containerFactory = "simpleJmsContainerFactory")
    public void processHelloTopic(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("ChefSubscriber > Greetings received from hello topic: " + o.getText().toUpperCase());
    }

    @JmsListener(destination="${activemq.destination.topic.virtual}",containerFactory = "topicConnectionFactory")
    public void receiveVirtualTopic(String message) {
        LOGGER.info("ChefSubscriber > Message received from virtual topic: " + message);
    }
}
