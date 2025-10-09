package ait.shop.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product")
@Schema(description = "Class that describes Product")
public class Product {

    @Schema(description = "Product unique identifier", example = "777", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /*
  Мы хотим, чтобы название продукта соответствовало определённым требованиям:
  1. Не должно быть короче трёх символов
  2. Не должно содержать цифры и спец.символы.
  3. Первая буква должна быть в верхнем регистре.
  4. Остальные буквы должны быть в нижнем регистре.
   */
    @Schema(description = "Product title", example = "Banana")
    @Column(name = "title")
    @NotNull(message = "Product title cannot be null")
    @NotBlank(message = "Product title cannot be blank")
    @Pattern(
            regexp = "[A-Z][a-z ]{2,}",
            message = "Product title should be at least three characters long and start with capital letter"
    )
    private String title;

    @Schema(description = "Product price", example = "3.99")
    @Column(name = "price")
    @DecimalMin(
            value = "0.01",
            message = "Product price should be greater or equal to 0.01"

    )
    @DecimalMax(
            value = "1000.00",
            inclusive = false,
            message = "Product price should be less than 1000.00"
    )
    private BigDecimal price;

    //if DB name = variable name => no ()
    @Schema(description = "Is product available", accessMode = Schema.AccessMode.READ_ONLY)
    @Column
    private boolean active;


    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("Product: id - %d, title - %s, price - %s, active - %s", id, title, price, active ? "yes" : "no");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return active == product.active && Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, active);
    }
}
