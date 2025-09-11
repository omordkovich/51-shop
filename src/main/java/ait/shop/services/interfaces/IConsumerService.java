package ait.shop.services.interfaces;

import ait.shop.model.dto.ConsumerDTO;

import java.util.List;

public interface IConsumerService {

    ConsumerDTO saveConsumer(ConsumerDTO consumer);

    ConsumerDTO getById(Long id);

    List<ConsumerDTO> getAllActiveConsumers();


}
