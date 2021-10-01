package com.owpk;

import com.owpk.common.Message;
import com.owpk.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class MQConsumer {
    private final EmailService emailService;

    @RabbitListener(queues = RabbitMQConstants.QUEUE)
    public void consume(Message msg) {
        log.info("MSG RECEIVED: " + msg);
        var txt = String.format("Order was created: %s%nTotal cost: %d",
                String.join("\n", msg.getItemNames()),
                msg.getTotalCost());
        emailService.sendSimpleMessage(msg.getUserEmail(), "TRANSNEFT TEST TASK", txt);
    }
}
