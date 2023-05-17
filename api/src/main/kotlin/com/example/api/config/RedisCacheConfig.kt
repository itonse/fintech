package com.example.api.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
@EnableCaching
class RedisCacheConfig {

    @Bean
    fun redisCacheManager(cacheFactory: RedisConnectionFactory): CacheManager {    // 캐시 매니저를 구성하여 리턴
        val redisCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))   // Redis 캐시의 키(Key)를 직렬화 (스트링 처리)
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))   //  Redis 캐시의 값(Value)을 직렬화 (스트링이 아니어도 처리)
            .entryTtl(Duration.ofMinutes(10))   // 캐시가 10분간 지속

        return RedisCacheManager
            .RedisCacheManagerBuilder
            .fromConnectionFactory(cacheFactory)
            .cacheDefaults(redisCacheConfig)
            .build()
    }
}