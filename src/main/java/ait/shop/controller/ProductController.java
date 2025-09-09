package ait.shop.controller;

import ait.shop.model.dto.ProductDTO;
import ait.shop.services.interfaces.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Controller", description = "Controller for operations with products")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    // POST /products
    @Operation(summary = "Create product", description = "Add new product", tags = {"Product"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProductDTO.class))})})

    @PostMapping
    public ProductDTO saveProduct(@Parameter(description = "Create product object") @RequestBody ProductDTO productDTO) {
        return service.saveProduct(productDTO);
    }

    @Operation(summary = "Get product by id", description = "Get product by id", tags = {"Product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = ProductDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid ip supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content),
    }
    )

    //GET /products?id=4
    @GetMapping("/{id}")
    public ProductDTO getById(@Parameter(description = "The id that needs to be fetched.", required = true) @PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping()
    public List<ProductDTO> getAll() {
        return service.getAllActiveProducts();
    }

    //Update: PUT -> /products/id и в теле поля, который мы хотим поменять
    @PutMapping("{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return service.update(id, productDTO);
    }

    //Delete: DELETE->products/id
    @DeleteMapping("/{productId}")
    public ProductDTO remove(@PathVariable("productId") Long id) {
        return service.deleteById(id);
    }


    //DELETE -> /products/by-title?title=Banana
    @DeleteMapping("/by-title")
    public ProductDTO removeByTitle(@RequestParam String title) {
        return service.deleteByTitle(title);
    }

    @PutMapping("/restore/{id}")
    public ProductDTO restoreById(@PathVariable Long id) {
        return service.restoreProductById(id);
    }

    @GetMapping("/count")
    public long getProductCount() {
        return service.getProductCount();
    }

    @GetMapping("/total-price")
    public BigDecimal getProductTotalPrice() {
        return service.getTotalPrice();
    }

    @GetMapping("/average-price")
    public BigDecimal getAveragePrice() {
        return service.getAveragePrice();
    }
    //POST /products/add = не правильно
    //POST /products = правильно

    //GET /products/getById/2 = не правильно
    //GET /products/2 = правильно

    //PUT /products/update/2 = не правильно
    //PUT /productS/2 = правильно

    //DELETE /products/2


}
