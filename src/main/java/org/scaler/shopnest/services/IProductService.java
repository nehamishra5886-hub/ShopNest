package org.scaler.shopnest.services;


import org.scaler.shopnest.dto.ProductDto;
import org.scaler.shopnest.models.Product;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(ProductDto productDto);
    boolean deleteProductById(Long id);
}
