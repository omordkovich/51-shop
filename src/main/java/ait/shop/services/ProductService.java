package ait.shop.services;

import ait.shop.model.dto.ProductDTO;
import ait.shop.model.entity.Product;
import ait.shop.repository.IProductRepository;
import ait.shop.services.interfaces.IProductService;
import ait.shop.services.mapping.ProductMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository repository;
    private final ProductMappingService mapper;

    //logger object
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);


    public ProductService(IProductRepository repository, ProductMappingService mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDto) {
        Product product = mapper.mapDtoToEntity(productDto);
//        product.setActive(true);
        return mapper.mapEntityToDto(repository.save(product));
    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = repository.findById(id).orElse(null);
        if (product == null || !product.isActive()) {
            throw new IllegalArgumentException(String.format("Product with id: %d does not exist", id));
        } ;
        return mapper.mapEntityToDto(product);
    }

    @Override
    public List<ProductDTO> getAllActiveProducts() {
//        logger.info("Info level message");
//        logger.warn("Warn level message");
//        logger.error("Error level message");

        return repository.findAll().stream()
                .filter(Product::isActive)
                .map(mapper::mapEntityToDto)
                //                .map(product -> mapper.mapEntityToDto(product))
                .toList();
    }

    @Override
    public ProductDTO update(Long id, ProductDTO product) {
        return null;
    }

    @Override
    public ProductDTO deleteById(Long id) {
        return null;
    }

    @Override
    public ProductDTO deleteByTitle(String title) {
        return null;
    }

    @Override
    public ProductDTO restoreProductById(Long id) {
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
