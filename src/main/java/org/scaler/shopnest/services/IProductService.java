package org.scaler.shopnest.services;


import org.scaler.shopnest.models.Product;

import java.util.List;

public interface IProductService {



    Product getProductById(Long id);


    //List<Product> getAllProducts();
}
