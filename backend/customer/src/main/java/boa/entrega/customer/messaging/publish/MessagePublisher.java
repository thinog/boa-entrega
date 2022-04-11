package boa.entrega.customer.messaging.publish;

public interface MessagePublisher {
    void publish(Object object, String to);
}
