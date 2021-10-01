package org.owpk.services;

import com.owpk.RabbitMQConstants;
import com.owpk.common.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQSenderService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Message msg) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE, RabbitMQConstants.ROUTING_KEY, msg);
        log.info("message sent: {}", msg);
    }
}
