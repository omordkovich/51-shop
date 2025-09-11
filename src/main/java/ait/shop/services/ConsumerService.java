package ait.shop.services;

import ait.shop.model.dto.ConsumerDTO;
import ait.shop.model.entity.Consumer;
import ait.shop.repository.IConsumerRepository;
import ait.shop.services.interfaces.IConsumerService;
import ait.shop.services.mapping.ConsumerMappingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService implements IConsumerService {
    private final IConsumerRepository repository;
    private final ConsumerMappingService mapper;

    public ConsumerService(IConsumerRepository repository, ConsumerMappingService mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ConsumerDTO saveConsumer(ConsumerDTO consumerDto) {
        Consumer consumer = mapper.mapDtoToEntity(consumerDto);
        //        consumer.setActive(true);
        return mapper.mapEntityToDto(repository.save(consumer));
    }

    @Override
    public ConsumerDTO getById(Long id) {
        Consumer consumer = repository.findById(id).orElse(null);
        if (consumer == null || !consumer.isActive()) return null;
        return mapper.mapEntityToDto(consumer);
    }

    @Override
    public List<ConsumerDTO> getAllActiveConsumers() {
        return repository.findAll().stream()
                .filter(Consumer::isActive)
                .map(mapper::mapEntityToDto)
                .toList();
    }
}
