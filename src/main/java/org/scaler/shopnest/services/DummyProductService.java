package org.scaler.shopnest.services;

import org.scaler.shopnest.models.Product;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DummyProductService implements IProductService {

    private RestTemplate restTemplate;

    public DummyProductService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public Product getProductById(Long id) {
        // Return a dummy product for demonstration purposes
        Product dummyProduct = new Product();
        dummyProduct.setId(id);
        dummyProduct.setName("Dummy Product");
        dummyProduct.setDescription("This is a dummy product.");
        dummyProduct.setPrice(BigDecimal.valueOf(9.99));
        dummyProduct.setQuantity(100);
        return dummyProduct;
    }

//    @Override
//    public List<Product> getAllProducts() {
//        return List.of();
//    }
}
