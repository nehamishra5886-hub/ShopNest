package org.scaler.shopnest.services;

import org.scaler.shopnest.dto.DummyJsonProductDTO;
import org.scaler.shopnest.models.Product;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
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

        try{
            String url = "https://dummyjson.com/products/{id}" ;
            ResponseEntity<DummyJsonProductDTO> response = restTemplate.getForEntity("https://dummyjson.com/products/{id}",
                    DummyJsonProductDTO.class, id);
            //return  from(response.getBody());
            if(response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
                return  from(response.getBody());
            }
            return null;
//        ResponseEntity<DummyJsonProductDTO> response = restTemplate.getForEntity("https://dummyjson.com/products/{id}",
//                   DummyJsonProductDTO.class, id);
//        return from(response.getBody());

        } catch (Exception e) {
            // Handle the exception (e.g., log it, throw a custom exception, etc.)
            System.err.println("Error fetching product: " + e.getMessage());
            return null; // or throw a custom exception
        }

    }

//    @Override
//    public List<Product> getAllProducts() {
//        return List.of();
//    }

    private Product from(DummyJsonProductDTO dummyJsonProductDTO) {
        Product product = new Product();
        product.setId(dummyJsonProductDTO.getId());
        product.setName(dummyJsonProductDTO.getTitle());
        product.setDescription(dummyJsonProductDTO.getDescription());
        product.setPrice(BigDecimal.valueOf(dummyJsonProductDTO.getPrice()));
        //product.setQuantity(dummyJsonProductDTO.);
        // Set other fields as needed
        return product;
    }
}