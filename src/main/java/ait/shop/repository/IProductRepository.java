package ait.shop.repository;

import ait.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {

// no methods at all
}
