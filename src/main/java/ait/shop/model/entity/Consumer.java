package ait.shop.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "consumer")

public class Consumer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean active;

    public Consumer() {
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
        return active == consumer.active && Objects.equals(id, consumer.id) && Objects.equals(name, consumer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active);
    }
}
