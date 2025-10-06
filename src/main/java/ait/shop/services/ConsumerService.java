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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        Consumer consumer = getActiveConsumerById(id);
        return mapper.mapEntityToDto(consumer);
    }

    @Override
    @Transactional
    public ConsumerDTO update(Long id, ConsumerDTO consumer) {
        Consumer targetConsumer = getActiveConsumerById(id);
        targetConsumer.setName(consumer.getName());
        return mapper.mapEntityToDto(targetConsumer);
    }

    @Override
    @Transactional
    public ConsumerDTO deleteById(Long id) {
        Consumer consumer = getActiveConsumerById(id);
        if (consumer == null || !consumer.isActive()) return null;
        consumer.setActive(false);
        return mapper.mapEntityToDto(consumer);
    }

    @Override
    @Transactional
    public ConsumerDTO deleteByName(String name) {
        List<Consumer> consumers = repository.findByName(name);
        if (consumers.isEmpty()) return null;
        Consumer consumer = consumers.get(0);
        consumer.setActive(false);
        return mapper.mapEntityToDto(consumer);
    }

    @Override
    @Transactional
    public ConsumerDTO restoreConsumer(Long id) {
        Consumer consumer = getActiveConsumerById(id);
        if (consumer == null || consumer.isActive()) {
            return null;
        }
        consumer.setActive(true);
        return mapper.mapEntityToDto(consumer);
    }

    @Override
    public long getCountActiveConsumers() {
        return repository
                .findAll()
                .stream()
                .filter(Consumer::isActive)
                .count();
    }

    @Override
    public BigDecimal getTotalPriceOfProductsInCartByActiveConsumerId(Long customerId) {
        Consumer consumer = getActiveConsumerById(customerId);
        Cart cart = consumer.getCart();
        return cart.getProducts().stream()
                .filter(Product::isActive)
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public double getAveragePriceOfActiveProductsInCartByActiveConsumerId(Long customerId) {
        Consumer consumer = getActiveConsumerById(customerId);
        return consumer
                .getCart()
                .getProducts()
                .stream()
                .filter(Product::isActive)
                .mapToDouble(x-> x.getPrice().doubleValue())
                .average().orElse(0.0);


    }

//    public BigDecimal getCustomersCartAveragePrice(Long customerId) {
//        Consumer consumer = getActiveConsumerById(customerId);
//        Cart cart = consumer.getCart();
//
//        BigDecimal sum = getTotalPriceOfProductsInCartByActiveConsumerId(customerId);
//        long activeProducts = cart.getProducts().stream().filter(Product::isActive).count();
//        return sum.divide(new BigDecimal(activeProducts), RoundingMode.HALF_UP);
//    }

    public Consumer getActiveConsumerById(Long id) {
        return repository.findById(id)
                .filter(Consumer::isActive)
                .orElseThrow(
                        () -> new IllegalArgumentException("Customer not found")
                );
    }

    @Override
    @Transactional
    public void addProductToConsumersCart(Long consumerId, Long productId) {
        Consumer consumer = getActiveConsumerById(consumerId);
        Product product = productService.getEntityById(productId);
        consumer.getCart().getProducts().add(product);
    }

    @Override
    @Transactional
    public void removeProductFromConsumersCart(Long consumerId, Long productId) {
        Consumer consumer = getActiveConsumerById(consumerId);
        Cart cart = consumer.getCart();
        Product product = productService.getEntityById(productId);
        cart.getProducts().remove(product);
    }

    @Override
    @Transactional
    public void cleanCart(Long consumerId) {
        Consumer consumer = getActiveConsumerById(consumerId);
        Cart cart = consumer.getCart();
        cart.getProducts().clear();
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