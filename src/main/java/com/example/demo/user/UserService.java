package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.redis.redisUser;
import com.example.demo.redis.redisUserRepo;
import com.example.demo.user.jpa.User;
import com.example.demo.user.jpa.UserRepository;

import user.user;

@Service
public class UserService {

    @Autowired
    private final RedisTemplate<String, redisUser> redisTemplate;
    private final redisUserRepo redisRepo;
    private final UserRepository repo;
    @Autowired
    private final KafkaTemplate<String, user> template;

    public UserService(UserRepository repo, RedisTemplate<String, redisUser> redisTemplate, redisUserRepo redisRepo, org.springframework.kafka.core.KafkaTemplate template) {
        this.redisRepo = redisRepo;
        this.repo = repo;
        this.redisTemplate = redisTemplate;
        this.template = template;
    }

    public User createUser(User user) {
        return repo.save(user);
    }

    public redisUser addUserRedis(redisUser redisUser) {
        String key = redisUser.getRedisId();
        redisRepo.save(redisUser);

        return (redisUser) redisRepo.getByredisId(key);
    }

    @Cacheable(value = "users")
    public User getUserByname(String name) {
        return repo.findUserByName(name);
    }

    @CachePut(value = "users", key = "#user.name")
    public User updateUser(User user) {
        return repo.save(user);
    }

    @CacheEvict(value = "users")
    public void deleteUser(String email) {
        repo.deleteUserByName(email);
    }

    public redisUser getUser(String redisId) {
        return (redisUser) redisTemplate.opsForValue().get(redisId);
    }

    public void sch(String name) {
        System.out.println(name);
    }

    // kafka part
    public void sendMessage(user User) {
        template.send("avroTestingT", User);
    }
}
