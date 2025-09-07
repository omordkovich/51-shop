package ait.shop.services;

import ait.shop.model.entity.Product;
import ait.shop.repository.IProductRepository;
import ait.shop.services.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product saveProduct(Product product) {
        product.setActive(true);
        return repository.save(product);
    }

    @Override
    public Product getById(Long id) {
        Product product = repository.findById(id).orElse(null);
        if (product == null || !product.isActive()) return null;
        return product;
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return repository.findAll().stream()
                .filter(Product::isActive)
                .toList();
    }

    @Override
    public Product update(Long id, Product product) {
        return null;
    }

    @Override
    public Product deleteById(Long id) {
        return null;
    }

    @Override
    public Product deleteByTitle(String title) {
        return null;
    }

    @Override
    public Product restoreProductById(Long id) {
        return null;
    }

    @Override
    public long getProductCount() {
        return 0;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getAveragePrice() {
        return null;
    }
}
