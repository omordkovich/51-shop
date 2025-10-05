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

    @PutMapping("/{consumerId}/add-product/{productId}")
    public void addProductToConsumersCart(
            @PathVariable Long consumerId,
            @PathVariable Long productId) {
        service.addProductToConsumersCart(consumerId, productId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @DeleteMapping("/by-name/{name}")
    public void deleteByName(@PathVariable String name) {
        service.deleteByName(name);
    }

    @PostMapping("/{id}/restore")
    public void restoreById(@PathVariable Long id) {
        service.restoreConsumer(id);
    }

    @GetMapping("/count")
    public Long count() {
        return service.getCountActiveConsumers();
    }

    @DeleteMapping("{consumerId}/clean-cart")
    public void cleanCart(@PathVariable Long consumerId) {
        service.cleanCart(consumerId);
    }

    @DeleteMapping("/{consumerId}/remove-product/{productId}")
    public void removeProductFromConsumersCart(
            @PathVariable Long consumerId,
            @PathVariable Long productId) {
        service.removeProductFromConsumersCart(consumerId, productId);
    }

    @PatchMapping("{id}/update")
    public void updateById(
            @PathVariable Long id,
            @RequestBody ConsumerDTO dto) {
        service.update(id, dto);
    }
}
