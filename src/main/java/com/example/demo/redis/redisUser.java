package com.example.demo.redis;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("user")
public class redisUser implements Serializable{
        private static final long serialVersionUID = 1L;
    @Id
    private String redisId;
    private String name;
    private String password;

    public redisUser(){}

    public redisUser( String name, String password, String redisId) {
        
        this.name = name;
        this.password = password;
        this.redisId=redisId;
    }

    public String getRedisId() {
        return redisId;
    }

    public void setRedisId(String redisId) {
        this.redisId = redisId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    
}
