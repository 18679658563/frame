package com.e8.frame.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * Created with IntelliJ IDEA.
 * Description:  redis缓存配置
 * User: silence
 * Date: 2019-03-04
 * Time: 上午9:38
 */
@Slf4j
@Configuration
@EnableCaching
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;//服务器地址

    @Value("${spring.redis.port}")
    private int port;//端口

    @Value("${spring.redis.timeout}")
    private int timeout;//超时时间

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;//jedis最大空闲数

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWait;//jedis最大阻塞等待时间

    @Value("${spring.redis.password}")
    private String password;//redis密码

    /**
     * 配置redis连接
     * @return
     */
    @Bean
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        if(!StringUtils.isEmpty(password)){
            return new JedisPool(jedisPoolConfig, host, port, timeout, password);
        } else {
            return new JedisPool(jedisPoolConfig,host,port,timeout);
        }
    }

    /**
     * 设置redis数据默认过期时间
     * 设置@cacheable序列化方式
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer)).entryTtl(Duration.ofHours(2));
        return redisCacheConfiguration;
    }

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object , Object> template = new RedisTemplate<>();
        //序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        //value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        //小范围指定白名单
        ParserConfig.getGlobalInstance().addAccept("com.e8.frame.model");
        ParserConfig.getGlobalInstance().addAccept("com.e8.frame.tools");
        //key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * 自定义缓存key的生成策略
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator(){
        return (target,method,params) -> {
            StringBuilder sb = new StringBuilder();
            //sb.append(target.getClass().getName());
            sb.append(method.getName());
            System.out.println(params+"\n\n\n");
            for(Object obj :params){
                System.out.println(obj+"\n\n\n");
                if(obj != null){
                    sb.append(obj.toString());
                }
            }
            return sb.toString();
        };
    }

}
