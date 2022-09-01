package com.oscarhanke.springbootredis.repository;

import com.oscarhanke.springbootredis.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDao {

    public static final String OBJECT_KEY = "Product";
    private final RedisTemplate redisTemplate;

    public Product save(Product product){
        redisTemplate.opsForHash().put(OBJECT_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll(){
        return redisTemplate.opsForHash().values(OBJECT_KEY);
    }

    public Product findById(Long id){
        return (Product) redisTemplate.opsForHash().get(OBJECT_KEY, id);
    }

    public String deleteById(Long id){
        redisTemplate.opsForHash().delete(OBJECT_KEY, id);
        return "Product with id: " + id + " removed.";
    }
}
