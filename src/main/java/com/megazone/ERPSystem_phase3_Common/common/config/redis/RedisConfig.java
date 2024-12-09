package com.megazone.ERPSystem_phase3_Common.common.config.redis;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@EnableCaching
@Configuration
@RequiredArgsConstructor
public class RedisConfig implements CachingConfigurer {

//    private final RedisInfo redisInfo;
//
//    @PostConstruct
//    public void logRedisInfo() {
//        log.info("Redis Nodes: {}", redisInfo.getClusterNodes());
//        log.info("Redis Password: {}", redisInfo.getPassword());
//        log.info("Redis Client Name: {}", redisInfo.getClientName());
//    }
//
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        // ip,port 등 서버 구성 관련 속성
//        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(redisInfo.getClusterNodes());
//        clusterConfiguration.setPassword(redisInfo.getPassword());
//
//        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
//                .clientName(redisInfo.getClientName())
//                .readFrom(ReadFrom.valueOf(redisInfo.getReadFrom()))
//                .build();
//
//        return new LettuceConnectionFactory(clusterConfiguration, clientConfiguration);
//    }
//
//    //JSON 직렬화/역직렬화 관련
//    private ObjectMapper objectMapper() {
//        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator
//                .builder()
//                .allowIfSubType(Object.class)
//                .build();
//
//        return new ObjectMapper()
//                .findAndRegisterModules()
//                .enable(SerializationFeature.INDENT_OUTPUT)
//                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
//                .registerModule(new JavaTimeModule())
//                .activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
//    }
//
//    @Bean
//    public RedisTemplate<String,Object> redisTemplate() {
//        final RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper()));
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(RedisSerializer.java());
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        return redisTemplate;
//    }
//
//
//    @Bean
//    public RedisCacheConfiguration cacheConfiguration() {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .disableCachingNullValues()
//                .computePrefixWith(cacheName -> cacheName.concat(":"))
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
//                        new GenericJackson2JsonRedisSerializer(objectMapper())
//                ));
//    }
//
//    @Override
//    @Bean
//    public CacheManager cacheManager() {
//        return RedisCacheManager.builder(this.redisConnectionFactory())
//                .cacheDefaults(this.cacheConfiguration())
//                .build();
//    }
//
//    @Override
//    public CacheErrorHandler errorHandler() {
//        return new CacheErrorHandler() {
//            @Override
//            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
//                log.warn(exception.getMessage(),exception);
//            }
//
//            @Override
//            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
//                log.warn(exception.getMessage(),exception);
//            }
//
//            @Override
//            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
//                log.warn(exception.getMessage(),exception);
//            }
//
//            @Override
//            public void handleCacheClearError(RuntimeException exception, Cache cache) {
//                log.warn(exception.getMessage(),exception);
//            }
//        };
//    }

    private final RedisInfo redisInfo;

    @PostConstruct
    public void logRedisInfo() {
        log.info("Redis Host: {}", redisInfo.getHost());
        log.info("Redis Port: {}", redisInfo.getPort());
        log.info("Redis Password: {}", redisInfo.getPassword());
        log.info("Redis Client Name: {}", redisInfo.getClientName());
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // 단일 노드 설정
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setHostName(redisInfo.getHost());
        standaloneConfig.setPort(redisInfo.getPort());
        standaloneConfig.setPassword(redisInfo.getPassword());

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .clientName(redisInfo.getClientName())
                .build();

        return new LettuceConnectionFactory(standaloneConfig, clientConfig);
    }

    private ObjectMapper objectMapper() {
        return new ObjectMapper()
                .findAndRegisterModules()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new JavaTimeModule());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper()));
        return template;
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper())));
    }

    @Override
    @Bean
    public CacheManager cacheManager() {
        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfiguration())
                .build();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                log.warn("Cache Get Error: {}", exception.getMessage(), exception);
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                log.warn("Cache Put Error: {}", exception.getMessage(), exception);
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                log.warn("Cache Evict Error: {}", exception.getMessage(), exception);
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                log.warn("Cache Clear Error: {}", exception.getMessage(), exception);
            }
        };
    }
}