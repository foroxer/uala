package com.juancastelli.uala.repository;

import com.juancastelli.uala.model.Tweet;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;


@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class HomeRepository {

    @Bean
    public RedisTemplate<Integer, Tweet> redisConfig(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Integer, Tweet> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(Integer.TYPE));
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());       // Add some specific configuration here. Key serializers, etc.
        return redisTemplate;
    }
}


