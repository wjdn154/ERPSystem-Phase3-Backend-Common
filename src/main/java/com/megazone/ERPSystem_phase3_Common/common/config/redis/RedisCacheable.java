package com.megazone.ERPSystem_phase3_Common.common.config.redis;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisCacheable {
    String cacheName();
    long expireTime() default -1;
}
