package com.juancastelli.uala.testUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

import java.io.IOException;

@Component
public class EmbededRedis {

    @Value("${spring.data.redis.port}")
    Integer port;
    private RedisServer redisServer;

    public void startRedis() throws IOException {

        redisServer = new RedisServer(port);
        redisServer.start();
    }

    public void stopRedis() throws IOException {
        redisServer.stop();
    }
}