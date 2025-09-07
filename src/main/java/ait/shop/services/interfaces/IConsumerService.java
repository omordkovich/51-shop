package ait.shop.services.interfaces;

import ait.shop.model.entity.Consumer;

import java.util.List;

public interface IConsumerService {

    Consumer saveConsumer(Consumer consumer);

    Consumer getById(Long id);

    List<Consumer> getAllActiveConsumers();


}
