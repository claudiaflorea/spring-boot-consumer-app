package com.practice.consumer.messageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomMessageConverter implements MessageConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomMessageConverter.class);

    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        Object o = object;
        String payload = null;
        try {
            payload = mapper.writeValueAsString(o);
            LOGGER.info("outbound json='{}'", payload);
        } catch (JsonProcessingException e) {
            LOGGER.error("error converting form object", e);
        }
        TextMessage message = session.createTextMessage();
        message.setText(payload);

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        TextMessage textMessage = (TextMessage) message;
        String payload = textMessage.getText();
        LOGGER.info("inbound json='{}'", payload);

        Object o = null;
        try {
            o = mapper.readValue(payload, Object.class);
        } catch (Exception e) {
            LOGGER.error("error converting to object", e);
        }

        return o;
    }
}
