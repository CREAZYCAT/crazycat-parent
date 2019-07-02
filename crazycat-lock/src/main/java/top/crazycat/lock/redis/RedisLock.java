package top.crazycat.lock.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import top.crazycat.common.util.RedisUtil;
import top.crazycat.lock.Lock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/7/2
 * description:
 */
public class RedisLock implements Lock {
    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);
    private static final Long DEFAULT_TIMEOUT = 60000L;
    public static final String KEY_PREFIX = "top:crazycat:lock:string:";
    private static RedisLock lock = new RedisLock();

    private RedisLock() {
    }

    public static Lock getInstance() {
        return lock;
    }

    @Override
    public boolean lock(String lockKey, Long timeout) {
        Jedis jedis = null;
        String realKey = KEY_PREFIX + lockKey;
        try {
            jedis = RedisUtil.getSingleton().getRedisClient();
            String ok = jedis.set(realKey, realKey, "NX", "PX", timeout);
            if ("OK".equals(ok)) {
                logger.info("lock success with lock_key:[{}]", realKey);
                return true;
            }
        } catch (Exception e) {
            logger.error("lock exception key:" + realKey, e);
        } finally {
            if (jedis != null) {
                RedisUtil.getSingleton().closeResource(jedis);
            }
        }
        return false;
    }

    @Override
    public boolean lockDefault(String lockKey) {
        return lock(lockKey, DEFAULT_TIMEOUT);
    }

    @Override
    public void release(String lockKey) {
        Jedis jedis = null;
        String realKey = KEY_PREFIX + lockKey;
        try {
            jedis = RedisUtil.getSingleton().getRedisClient();
            jedis.del(realKey);
            logger.info("release success with lock_key:[{}]", realKey);
        } catch (Exception e) {
            logger.error("release exception key:" + realKey, e);
        } finally {
            if (jedis != null) {
                RedisUtil.getSingleton().closeResource(jedis);
            }
        }
    }
}
