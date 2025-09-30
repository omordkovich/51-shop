package ait.shop.model.dto;

import jakarta.persistence.*;

import java.util.Objects;


public class ConsumerDTO {

    private Long id;
    private String name;
    private CartDTO cart;

    public ConsumerDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CartDTO getCart() {

        return cart;
    }

    public void setCart(CartDTO cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return String.format("ConsumerDTO: id - %d, name - %s", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ConsumerDTO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cart);
    }
}
