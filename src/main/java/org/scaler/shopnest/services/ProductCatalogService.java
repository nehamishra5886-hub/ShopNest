package org.scaler.shopnest.services;

import org.scaler.shopnest.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCatalogService {

    public List<Product> getAllProducts() {
        // Logic to retrieve all products from the database
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Description for Product 1");
        product.setPrice(new java.math.BigDecimal("10.99"));
        product.setQuantity(100);
        product.setCategory(org.scaler.shopnest.models.enums.Category.ELECTRONICS);

        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }
}
