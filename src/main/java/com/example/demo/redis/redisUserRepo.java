package com.example.demo.redis;


import org.springframework.data.repository.CrudRepository;

public interface redisUserRepo extends CrudRepository<redisUser, String>{

    redisUser getByredisId(String id);
} 