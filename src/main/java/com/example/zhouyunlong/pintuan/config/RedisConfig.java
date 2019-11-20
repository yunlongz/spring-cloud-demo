package com.example.zhouyunlong.pintuan.config;

import com.alicp.jetcache.autoconfigure.JedisPoolFactory;
import com.alicp.jetcache.autoconfigure.RedisAutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/15 22:48
 * 4
 */
@Configuration
@Slf4j
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

//    @Value("${spring.redis.password}")
//    private String password;

//    @Value("${spring.redis.block-when-exhausted}")
//    private boolean blockWhenExhausted;

    @Bean
    public JedisPool redisPoolFactory() {
        log.info("JedisPool注入成功！！");
        log.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(true);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        return new JedisPool(jedisPoolConfig, host, port, timeout);
    }
    @Bean(name = "defaultPool")
    @DependsOn(RedisAutoConfiguration.AUTO_INIT_BEAN_NAME)
    public JedisPoolFactory defaultPool() {
        return new JedisPoolFactory("remote.default", JedisPool.class);
    }
}