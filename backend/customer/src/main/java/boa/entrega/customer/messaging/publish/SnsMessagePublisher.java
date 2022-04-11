package boa.entrega.customer.messaging.publish;

import org.springframework.stereotype.Component;

@Component
public class SnsMessagePublisher implements MessagePublisher {
    @Override
    public void publish(Object message, String to) {

    }
}
