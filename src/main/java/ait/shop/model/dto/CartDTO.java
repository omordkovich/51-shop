package ait.shop.model.dto;



import java.util.List;
import java.util.Objects;

/**
 * @author Oleg Mordkovich
 * {@code @date} 30.09.2025
 */
public class CartDTO {

    private Long id;
    private List<ProductDTO> products;

    public CartDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartDTO cartDTO)) return false;
        return Objects.equals(id, cartDTO.id) && Objects.equals(products, cartDTO.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products);
    }

    @Override
    public String toString() {
        return String.format("CartDTO: id - %d, products - %s", id, products == null ? 0 : products.size());
    }
}
