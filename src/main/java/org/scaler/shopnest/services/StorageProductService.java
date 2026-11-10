package org.scaler.shopnest.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scaler.shopnest.dto.ProductDto;
import org.scaler.shopnest.models.Product;
import org.scaler.shopnest.models.enums.State;
import org.scaler.shopnest.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@Slf4j
//@RequiredArgsConstructor

public class StorageProductService implements IProductService{

    private final ProductRepository productRepository;
    public StorageProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product getProductById(Long id) {
        log.info("Reading product from DB: "+id);
        //Optional<Product> productOptional = productRepository.findById(id);
        //return productOptional.orElse(null);
//        if (productOptional.isPresent()) {
//            return productOptional.get();
//        } else {
//            return null;
//        }


        return productRepository.findById(id).orElse(null);

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product==null) return false;
        product.setState(State.INACTIVE);
        product.setLastUpdatedAt(String.valueOf(new Date()));
        productRepository.save(product);
        return true;
    }
    @Override
    public Product createProduct(ProductDto productDto) {
        //Product product = productMapper.toEntity(productDto);
//        product.setCategory(findOrCreateCategory(product.getCategory()));
//        product.setCreatedAt(new Date());
//        product.setLastUpdatedAt(new Date());
//        product.setState(State.ACTIVE);
//        return productRepository.save(product);
        return null;
    }
}
