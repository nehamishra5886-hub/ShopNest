package org.scaler.shopnest.controller;

import org.scaler.shopnest.dto.CategoryDto;
import org.scaler.shopnest.dto.ProductDto;
import org.scaler.shopnest.dto.ProductPitchDto;
import org.scaler.shopnest.models.Product;
import org.scaler.shopnest.services.IPitchService;
import org.scaler.shopnest.services.IProductService;
import org.scaler.shopnest.services.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ShopNest/products")
public class ProductController {


    private IProductService productService;

    private IPitchService pitchService;


    private final ProductCatalogService productCatalogService;

    private final IProductService supplierService;

    public ProductController(IProductService productService,
                             ProductCatalogService productCatalogService,
                             IPitchService pitchService,
                             @Qualifier("dummyProductService") IProductService supplierService) {
        this.productService = productService;
        this.productCatalogService = productCatalogService;
        this.pitchService = pitchService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        // Logic to retrieve all products from the database
        //return productCatalogService.getAllProducts();
        List<Product> products = productService.getAllProducts();
//        List<ProductDto> productDtos = new ArrayList<>();
//        for(Product product: products) {
//            productDtos.add(productMapper.toDto(product));
//        }
        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
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

    private ProductDto from(Product product) {
        ProductDto productDTO = new ProductDto();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        //productDTO.setQuantity(product.getQuantity());

        CategoryDto categoryDTO = new CategoryDto();
        categoryDTO.setId(product.getCategory().getId());
        // Set other fields as needed
        return productDTO;
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProductById(id);
        if (isDeleted) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/pitch")
    public ResponseEntity<ProductPitchDto> generateProductPitch(@PathVariable("id") Long id) {
        if(id==null || id < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductPitchDto pitchDto = new ProductPitchDto();
        pitchDto.setId(product.getId());
        pitchDto.setPitchMessage(pitchService.generatePitch(product));


        return new ResponseEntity<>(pitchDto, HttpStatus.OK);
    }
}
