package com.practice.consumer.configuration;

import javax.jms.ConnectionFactory;

import com.practice.consumer.messageConverter.CustomMessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

@Slf4j
@EnableJms
@Configuration
@RequiredArgsConstructor
public class JmsConfiguration {

    private CustomMessageConverter             customMessageConverter = new CustomMessageConverter();
    private DefaultJmsListenerContainerFactory factory                = new DefaultJmsListenerContainerFactory();

    @Bean
    public JmsListenerContainerFactory<?> queueConnectionFactory(ConnectionFactory connectionFactory) {
        factory.setMessageConverter(customMessageConverter);
        factory.setErrorHandler(t -> {
            throw new RuntimeException("Error in listener!", t);
        });
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-10");
        factory.setRecoveryInterval(1000L);
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicConnectionFactory(ConnectionFactory connectionFactory) {
        factory.setMessageConverter(customMessageConverter);
        factory.setErrorHandler(t -> {
            throw new RuntimeException("Error in listener!", t);
        });
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-10");
        factory.setRecoveryInterval(1000L);
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    JmsListenerContainerFactory<?> simpleJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory simpleFactory = new SimpleJmsListenerContainerFactory();
        simpleFactory.setConnectionFactory(connectionFactory);
        simpleFactory.setPubSubDomain(true);
        simpleFactory.setErrorHandler(t -> {
            throw new RuntimeException("Error in listener!", t);
        });
        return simpleFactory;
    }

}
