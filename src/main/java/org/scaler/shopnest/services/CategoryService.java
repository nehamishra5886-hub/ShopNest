package org.scaler.shopnest.services;

import org.scaler.shopnest.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Double getAvgPriceForCategory(String category) {
        return categoryRepo.findAvgPriceForCategory(category);
    }
}