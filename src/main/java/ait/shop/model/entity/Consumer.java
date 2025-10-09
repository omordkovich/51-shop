package ait.shop.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Table(name = "consumer")

public class Consumer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Consumer name cannot be null")
    @NotBlank(message = "Consumer name cannot be blank")
    @Pattern(
            regexp = "[A-Z][a-z ]{2,}",
            message = "Consumer name should be at least three characters long and start with capital letter"
    )
    @Column
    private String name;

    @Column
    private boolean active;

    @OneToOne(mappedBy = "consumer", cascade = CascadeType.ALL)
    private Cart cart;

    public Consumer() {
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("Consumer: id - %d, name - %s, active - %s", id, name, active ? "yes" : "no");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Consumer consumer)) return false;
        return active == consumer.active && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name) && Objects.equals(cart, consumer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, cart);
    }
}
