package ait.shop.controller;

import ait.shop.model.dto.ConsumerDTO;
import ait.shop.services.ConsumerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerService service;

    public ConsumerController(ConsumerService service) {
        this.service = service;
    }

    @PostMapping
    public ConsumerDTO save(@RequestBody ConsumerDTO dto) {
        return service.save(dto);
    }

    @GetMapping("/{id}")
    public ConsumerDTO findConsumerById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping()
    public List<ConsumerDTO> getAll() {
        return service.getAllActiveConsumers();
    }

    @PostMapping("/{consumerId}/add-product/{productId}")
    public void addProductToConsumersCart(
            @PathVariable Long consumerId,
            @PathVariable Long productId) {
    }
}
