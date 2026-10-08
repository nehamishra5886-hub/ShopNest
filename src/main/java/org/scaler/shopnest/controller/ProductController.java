package org.scaler.shopnest.controller;

import org.scaler.shopnest.models.Product;
import org.scaler.shopnest.services.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ShopNest")
public class ProductController {

    @Autowired
    ProductCatalogService productCatalogService;


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        // Logic to retrieve all products from the database
        return productCatalogService.getAllProducts();
    }
}
