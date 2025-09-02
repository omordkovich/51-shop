package ait.shop.controller;

import ait.shop.model.entity.Product;
import ait.shop.services.interfaces.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    // POST /products
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    //GET /products?id=4
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
       return service.getById(id);
    }

    @GetMapping()
    public List<Product> getAll() {
        return service.getAllActiveProducts();
    }

    //Update: PUT -> /products/id и в теле поля, который мы хотим поменять
    @PutMapping("{/id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{productId}")
    public Product remove(@PathVariable("productId") Long id) {
        return service.deleteById(id);
    }

    //POST /products/add = не правильно
    //POST /products = правильно

    //GET /products/getById/2 = не правильно
    //GET /products/2 = правильно

    //PUT /products/update/2 = не правильно
    //PUT /productS/2 = правильно

    //DELETE /products/2


}
