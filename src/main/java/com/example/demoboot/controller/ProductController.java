package com.example.demoboot.controller;

import com.example.demoboot.model.Category;
import com.example.demoboot.model.Product;
import com.example.demoboot.service.impl.CategoryServiceImpl;
import com.example.demoboot.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAllProduct() {
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(productOptional.get().getId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @GetMapping("/category_id/{id}")
    public ResponseEntity<Iterable<Product>> findAllByCategory_Id(@PathVariable Long id) {
        List<Product> products = (List<Product>) productService.findAllByCategoryId(id);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> showViewById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<Iterable<Product>> showViewById(@PathVariable String name) {
        Iterable<Product> product = productService.findAllByCategoryName(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/find-by-price")
    public ResponseEntity<Iterable<Product>> showViewById(@RequestParam int from, @RequestParam int to) {
        Iterable<Product> product = productService.findAllByPriceBetween(from, to);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
