package com.example.demoboot.service.impl;

import com.example.demoboot.model.Product;
import com.example.demoboot.repository.IProductRepository;
import com.example.demoboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    public Iterable<Product> findAllByCategory_Id(Long id){
        return productRepository.findAllByCategory_Id(id);
    }
}
