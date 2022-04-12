package boa.entrega.supplier.messaging.publish;

public interface MessagePublisher {
    void publish(Object object, String to);
}
