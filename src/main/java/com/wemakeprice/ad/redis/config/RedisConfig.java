package com.wemakeprice.ad.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ComponentScan("com.wemakeprice.ad.redis")

public class RedisConfig {

    @Value("${redis.host}")
    public String redisHost;

    @Value("${redis.port}")
    public int redisPort;

    @Value("${redis.password}")
    public String redisPassword;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        System.out.println("redisHost : " + redisHost + " / redisPort : " + redisPort + " / redisPassword : " + redisPassword);


        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration);


        return factory;

    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }


}
