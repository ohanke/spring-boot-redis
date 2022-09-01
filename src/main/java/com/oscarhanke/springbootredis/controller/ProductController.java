package com.oscarhanke.springbootredis.controller;

import com.oscarhanke.springbootredis.entity.Product;
import com.oscarhanke.springbootredis.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDao productDao;

    @PostMapping
    public Product save(@RequestBody Product product){
        return productDao.save(product);
    }

    @GetMapping("{id}")
    public Product findById(@PathVariable Long id){
        return productDao.findById(id);
    }

    @GetMapping("all")
    public List<Product> findAll(){
        return productDao.findAll();
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable Long id){
        return productDao.deleteById(id);
    }
}
