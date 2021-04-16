package com.practice.consumer.subscriber;

import javax.jms.JMSException;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RestaurantSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantSubscriber.class);

    @JmsListener(destination = "${activemq.destination.queue.restaurant}", containerFactory = "queueConnectionFactory")
    public void processQueue(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("RestaurantSubscriber > Message Received from restaurant queue " + o.getText());
    }

    @JmsListener(destination = "${activemq.destination.topic.restaurant}",subscription = "restaurant_topic", containerFactory = "topicConnectionFactory")
    public void processTopic(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("RestaurantSubscriber > Message Received from restaurant topic " + o.getText());
    }

    @JmsListener(destination = "${activemq.destination.topic.chef}",subscription = "chef_topic", containerFactory = "topicConnectionFactory")
    public void processChefTopic(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("RestaurantSubscriber > Message Received from chef topic " + o.getText());
    }

    @JmsListener(destination = "${activemq.destination.topic.hello}", subscription = "hello_topic", containerFactory = "simpleJmsContainerFactory")
    public void processHelloTopic(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("RestaurantSubscriber > Greetings received from hello topic: " + o.getText().toUpperCase());
    }

    @JmsListener(destination="${activemq.destination.topic.virtual}",containerFactory = "topicConnectionFactory")
    public void receiveVirtualTopic(String message) {
        LOGGER.info("RestaurantSubscriber > Message received from virtual topic: " + message);
    }
}
