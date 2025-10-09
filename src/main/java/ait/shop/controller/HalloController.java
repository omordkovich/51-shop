package ait.shop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oleg Mordkovich
 * {@code @date} 09.10.2025
 */

@RestController
@RequestMapping("/hello")
public class HalloController {
    private final String hello;

    public HalloController(@Value("${messages.hello}") String hello) {
        this.hello = hello;
    }

    @GetMapping
    public String sayHello() {
        return hello;
    }

}
