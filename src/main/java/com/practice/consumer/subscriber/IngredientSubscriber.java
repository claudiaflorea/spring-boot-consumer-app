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
public class IngredientSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientSubscriber.class);

    @JmsListener(destination = "${activemq.destination.queue.ingredient}", containerFactory = "queueConnectionFactory")
    public void processQueue(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("IngredientSubscriber > Message Received from ingredient queue " + o.getText());
    }

    @JmsListener(destination = "${activemq.destination.topic.ingredient}", subscription = "ingredient_topic", containerFactory = "topicConnectionFactory")
    public void processTopic(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("IngredientSubscriber > Message Received from ingredient topic " + o.getText());
    }

    @JmsListener(destination = "${activemq.destination.topic.hello}", subscription = "hello_topic", containerFactory = "simpleJmsContainerFactory")
    public void processHelloTopic(ActiveMQTextMessage o) throws JMSException {
        LOGGER.info("IngredientSubscriber > Greetings received from hello topic: " + o.getText().toUpperCase());
    }

    @JmsListener(destination="${activemq.destination.topic.virtual}",containerFactory = "topicConnectionFactory")
    public void receiveVirtualTopic(String message) {
        LOGGER.info("IngredientSubscriber > Message received from virtual topic: " + message);
    }
}
