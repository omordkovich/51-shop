package ait.shop.services;

import ait.shop.model.dto.ConsumerDTO;
import ait.shop.model.entity.Cart;
import ait.shop.model.entity.Consumer;
import ait.shop.model.entity.Product;
import ait.shop.repository.IConsumerRepository;
import ait.shop.services.interfaces.IConsumerService;
import ait.shop.services.interfaces.IProductService;
import ait.shop.services.mapping.ConsumerMappingService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService implements IConsumerService {
    private final IConsumerRepository repository;
    private final ConsumerMappingService mapper;
    private final IProductService productService;

    public ConsumerService(IConsumerRepository repository, ConsumerMappingService mapper, IProductService productService) {
        this.repository = repository;
        this.mapper = mapper;
        this.productService = productService;
    }

    @Override
    @Transactional
    public ConsumerDTO save(ConsumerDTO consumerDto) {
        Consumer entity = mapper.mapDtoToEntity(consumerDto);
        //        consumer.setActive(true);
        Cart cart = new Cart();
        cart.setConsumer(entity);
        entity.setCart(cart);

        repository.save(entity);
        return mapper.mapEntityToDto(entity);
    }

    @Override
    public ConsumerDTO getById(Long id) {
        Consumer consumer = repository.findById(id).orElse(null);
        if (consumer == null || !consumer.isActive()) return null;
        return mapper.mapEntityToDto(consumer);
    }

    public Consumer getActiveById(Long id) {
        return repository.findById(id)
                .filter(Consumer::isActive)
                .orElseThrow(
                        () -> new IllegalArgumentException()
                );
    }

    @Override
    @Transactional
    public void addProductToConsumersCart(Long consumerId, Long productId) {
        Consumer consumer = getActiveById(consumerId);
        Product product = productService.getEntityById(productId);
        consumer.getCart().getProducts().add(product);
    }

    @Override
    public List<ConsumerDTO> getAllActiveConsumers() {
        return repository.findAll()
                .stream()
                .filter(Consumer::isActive)
                .map(mapper::mapEntityToDto)
                .toList();
    }
}
