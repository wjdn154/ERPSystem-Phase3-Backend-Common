package com.megazone.ERPSystem_phase3_Common.common.config.aop;


import com.megazone.ERPSystem_phase3_Common.common.config.redis.RedisCacheable;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
@RequiredArgsConstructor
public class RedisCacheAspect {

    private final RedisTemplate<String, Object> redisTemplate;

    @Around("@annotation(com.megazone.ERPSystem_phase3_Common.common.config.redis.RedisCacheable)")
    public Object cacheableProcess(ProceedingJoinPoint joinPoint) throws Throwable {

        RedisCacheable redisCacheable =  getCacheable(joinPoint);
        final String cacheKey = generateKey(redisCacheable.cacheName(),joinPoint);

        Object[] parameterValues = joinPoint.getArgs();

        if (Boolean.TRUE.equals(redisTemplate.hasKey(cacheKey)) && !confirmBypass()) {
            return redisTemplate.opsForValue().get(cacheKey);
        }

        final Object methodReturnValue = joinPoint.proceed();
        final long cacheTTL = redisCacheable.expireTime();
        if (cacheTTL < 0) {
            redisTemplate.opsForValue().set(cacheKey,methodReturnValue);
        } else {
            redisTemplate.opsForValue().set(cacheKey,methodReturnValue,cacheTTL, TimeUnit.SECONDS);
        }

        return methodReturnValue;
    }

    private boolean confirmBypass() {
        return false; // 항상 캐시를 사용
    }

    private RedisCacheable getCacheable(ProceedingJoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();

        return AnnotationUtils.getAnnotation(method, RedisCacheable.class);
    }

    private String generateKey(String cacheName, ProceedingJoinPoint joinPoint) {

        String generatedKey = StringUtils.arrayToCommaDelimitedString(joinPoint.getArgs());

        return String.format("%s:%s", cacheName, generatedKey);
    }
}
