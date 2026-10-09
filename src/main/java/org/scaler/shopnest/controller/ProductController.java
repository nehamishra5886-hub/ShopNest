package org.scaler.shopnest.controller;

import org.scaler.shopnest.dto.CategoryDTO;
import org.scaler.shopnest.dto.ProductDTO;
import org.scaler.shopnest.models.Product;
import org.scaler.shopnest.services.IProductService;
import org.scaler.shopnest.services.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ShopNest/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private final ProductCatalogService productCatalogService;

    public ProductController(IProductService productService, ProductCatalogService productCatalogService) {
        this.productService = productService;
        this.productCatalogService = productCatalogService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        // Logic to retrieve all products from the database
        return productCatalogService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        // Logic to retrieve a product by its ID from the database
        if (id < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(from(product), HttpStatus.OK);

    }

    private ProductDTO from(Product product) {
        ProductDTO productDTO = new ProductDTO();
        //productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(product.getCategory().getId());
        // Set other fields as needed
        return productDTO;
    }
}
