package ait.shop.services.interfaces;

import ait.shop.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {

    Product saveProduct(Product product);

    Product getById(Long id);

    List<Product> getAllActiveProducts();

    Product update(Long id, Product product);

    Product deleteById(Long id);

    Product deleteByTitle(String title);

    Product restoreProductById(Long id);

    long getProductCount();

    BigDecimal getTotalPrice();

    BigDecimal getAveragePrice();
}
