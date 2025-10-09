package ait.shop.exception.handling.exceptions.consumer;

public class ConsumerNotFoundException extends RuntimeException {
    public ConsumerNotFoundException(Long id) {
        super(String.format("Consumer with id: %d was not found", id));
    }
    public ConsumerNotFoundException(String name) {
        super(String.format("Consumer with name: %s was not found", name));
    }
}
