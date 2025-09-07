package ait.shop.services;

import ait.shop.model.entity.Consumer;
import ait.shop.repository.IConsumerRepository;
import ait.shop.services.interfaces.IConsumerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService implements IConsumerService {
    private final IConsumerRepository repository;

    public ConsumerService(IConsumerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Consumer saveConsumer(Consumer consumer) {
        consumer.setActive(true);
        return repository.save(consumer);
    }

    @Override
    public Consumer getById(Long id) {
        Consumer consumer = repository.findById(id).orElse(null);
        if (consumer == null || !consumer.isActive()) return null;
        return consumer;
    }

    @Override
    public List<Consumer> getAllActiveConsumers() {
        return repository.findAll().stream()
                .filter(Consumer::isActive)
                .toList();
    }
}
