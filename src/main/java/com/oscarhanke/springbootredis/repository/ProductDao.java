package com.oscarhanke.springbootredis.repository;

import com.oscarhanke.springbootredis.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDao implements Serializable {

    public static final String HASH_KEY = "Product";
    private final RedisTemplate redisTemplate;

    public Product save(Product product){
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Product findById(int id){
        return (Product) redisTemplate.opsForHash().get(id, HASH_KEY);
    }

    public String deleteById(int id){
        redisTemplate.opsForHash().delete(id, HASH_KEY);
        return "Product with id: " + id + " removed.";
    }
}
