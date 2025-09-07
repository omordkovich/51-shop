package ait.shop.controller;

import ait.shop.model.entity.Consumer;
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
    public Consumer saveConsumer(@RequestBody Consumer consumer) {
        return service.saveConsumer(consumer);
    }

    @GetMapping("/{id}")
    public Consumer findConsumerById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping()
    public List<Consumer> getAll() {
        return service.getAllActiveConsumers();
    }
}
