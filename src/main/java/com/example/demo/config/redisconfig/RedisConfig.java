package com.example.demo.config.redisconfig;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.demo.redis.redisUser;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, redisUser> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, redisUser> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // Use Jackson2JsonRedisSerializer with default settings
        Jackson2JsonRedisSerializer<redisUser> jsonSerializer = new Jackson2JsonRedisSerializer<>(redisUser.class);

        // Set serializers
        template.setKeySerializer(new StringRedisSerializer());       // keys as strings
        template.setValueSerializer(jsonSerializer);                  // values as JSON
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jsonSerializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisSerializationContext.SerializationPair<Object> valueSerializer
                = RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());

        RedisSerializationContext.SerializationPair<String> keySerializer
                = RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());

        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(keySerializer)
                .serializeValuesWith(valueSerializer)
                .entryTtl(Duration.ofMinutes(1)); // Optional TTL

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfig)
                .build();
    }

}
