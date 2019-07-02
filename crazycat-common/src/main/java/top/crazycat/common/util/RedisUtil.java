package top.crazycat.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/7/2
 * description:classpath下必须有redis.properties 或已经注册到spring的JedisPool
 */
public class RedisUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private int maxIdle;
    private int maxWait;
    private boolean testOnBorrow;
    private String host;
    private int port;
    private int timeout;
    private String password;
    private int database;
    private String clientName;
    private int maxTimeoutCount;
    private static JedisPool pool;
    private static RedisUtil factory;

    public static RedisUtil getSingleton() {
        if (factory == null) {
            factory = RedisUtil.Instance.instance;
        }

        return factory;
    }

    public String get(String key) {
        Jedis jedis = null;

        Object var4;
        try {
            jedis = getSingleton().getRedisClient();
            String var3 = jedis.get(key);
            return var3;
        } catch (Exception var8) {
            logger.error(var8.getMessage(), var8);
            var4 = null;
        } finally {
            this.closeResource(jedis);
        }

        return (String)var4;
    }

    private RedisUtil() {
        this.maxIdle = 8;
        this.maxWait = 2000;
        this.testOnBorrow = true;
        this.database = 0;
        this.maxTimeoutCount = 3;
        logger.info("开始读取redis.properties文件");
        Properties pro = PropertiesUtil.getConfig("redis.properties");
        logger.debug("redis.properties:" + pro);
        String value = (String)pro.get("redis.maxIdle");
        if (null != value && !"".equals(value)) {
            this.maxIdle = Integer.parseInt(value);
        }

        value = (String)pro.get("redis.maxWait");
        if (null != value && !"".equals(value)) {
            this.maxWait = Integer.parseInt(value);
        }

        value = (String)pro.get("redis.testOnBorrow");
        if (null != value && !"".equals(value)) {
            this.testOnBorrow = Boolean.parseBoolean(value);
        }

        value = (String)pro.get("redis.host");
        if (null != value && !"".equals(value)) {
            this.host = value;
        }

        value = (String)pro.get("redis.port");
        if (null != value && !"".equals(value)) {
            this.port = Integer.parseInt(value);
        }

        value = (String)pro.get("redis.timeout");
        if (null != value && !"".equals(value)) {
            this.timeout = Integer.parseInt(value);
        }

        value = (String)pro.get("redis.password");
        if (null != value && !"".equals(value)) {
            this.password = value;
        }

        value = (String)pro.get("redis.database");
        if (null != value && !"".equals(value)) {
            this.database = Integer.parseInt(value);
        }

        value = (String)pro.get("redis.clientName");
        if (null != value && !"".equals(value)) {
            this.clientName = value;
        }

        value = (String)pro.get("redis.maxTimeoutCount");
        if (null != value && !"".equals(value)) {
            this.maxTimeoutCount = Integer.parseInt(value);
        }

    }

    public synchronized JedisPool getJedisPool() {
        if (pool == null) {
            pool = CommandFactory.getBean(JedisPool.class);
        }

        if (null == pool) {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxIdle(this.maxIdle);
            poolConfig.setMaxWaitMillis((long)this.maxWait);
            poolConfig.setTestOnBorrow(this.testOnBorrow);
            if (this.clientName != null) {
                pool = new JedisPool(poolConfig, this.host, this.port, this.timeout, this.password, this.database, this.clientName);
            } else if (this.database != 0) {
                pool = new JedisPool(poolConfig, this.host, this.port, this.timeout, this.password, this.database);
            } else if (this.password != null) {
                pool = new JedisPool(poolConfig, this.host, this.port, this.timeout, this.password);
            } else {
                pool = new JedisPool(poolConfig, this.host, this.port, this.timeout);
            }
        }

        return pool;
    }

    public synchronized Jedis getRedisClient() {
        byte var1 = 0;

        try {
            return this.getJedisPool().getResource();
        } catch (Exception var3) {
            if (var3 instanceof JedisConnectionException) {
                int timeoutCount = var1 + 1;
                logger.warn("getJedis timeoutCount={}", timeoutCount);
                logger.error("getJedis error message={}", var3.getMessage(), var3);
                if (timeoutCount > this.maxTimeoutCount) {
                    ;
                }
            } else {
                if (pool != null) {
                    logger.warn("jedisInfo ... NumActive=" + pool.getNumActive() + ", NumIdle=" + pool.getNumIdle() + ", NumWaiters=" + pool.getNumWaiters() + ", isClosed=" + pool.isClosed());
                }

                logger.error("GetJedis error,", var3);
            }

            return null;
        }
    }

    public boolean handleJedisException(Exception jedisException) {
        if (jedisException instanceof JedisConnectionException) {
            logger.error("Redis connection lost.", jedisException);
        } else if (jedisException instanceof JedisDataException) {
            if (jedisException.getMessage() == null || !jedisException.getMessage().contains("READONLY")) {
                return false;
            }

            logger.error("Redis connection are read-only slave.", jedisException);
        } else {
            logger.error("Jedis exception happen.", jedisException);
        }

        return true;
    }

    public void closeResource(Jedis jedis) {
        if (pool != null && jedis != null) {
            try {
                jedis.close();
            } catch (Exception var4) {
                logger.error("return back jedis failed, will fore close the jedis.", var4);
            }
        }

    }

    private static class Instance {
        static final RedisUtil instance = new RedisUtil();

        private Instance() {
        }
    }
}
