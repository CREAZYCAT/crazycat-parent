package top.crazycat.lock.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.crazycat.lock.Lock;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/7/2
 * description:
 */
public class ZookeeperLock implements Lock {
    private static final Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);

    private static final String ROOT_PATH = "/lock/crazycat/string/";

    private CuratorFramework client;
    private ThreadLocal<LockData> processMutexThreadLocal = new ThreadLocal<>();


    public ZookeeperLock(String zkAddress){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(zkAddress, retryPolicy);
        client.start();
    }

    @Override
    public boolean lock(String s, Long aLong) {
        try {
            LockData lockData = processMutexThreadLocal.get();//同一线程不重复创建，保证可重入
            InterProcessMutex mutex;
            if(null == lockData){
                mutex = new InterProcessMutex(client, ROOT_PATH+s);
                lockData = new LockData(mutex,0);
                processMutexThreadLocal.set(lockData);
            }else {
                mutex = lockData.mutex;
            }
            if(-1L == aLong){
                mutex.acquire();
                lockData.lockCount++;
            }else {
                mutex.acquire(aLong,TimeUnit.MILLISECONDS);
            }
            logger.info("zk lock success by path:{}",s);
            return true;
        } catch (Exception e) {
            logger.error("zk lock exception",e);
        }
        return false;
    }

    @Override
    public boolean lockDefault(String s) {
        return lock(s,-1L);
    }

    @Override
    public void release(String s) {
        LockData lockData = processMutexThreadLocal.get();
        try {
            if(null != lockData){
                lockData.mutex.release();
                lockData.lockCount--;
                if(lockData.lockCount == 0){
                    processMutexThreadLocal.remove();
                }
            }
            logger.info("zk release success by path:{}",s);
        } catch (Exception e) {
            logger.error("zk release exception",e);
        }
    }

    static class LockData {
        InterProcessMutex mutex;
        Integer lockCount;

        LockData(InterProcessMutex mutex, Integer lockCount) {
            this.mutex = mutex;
            this.lockCount = lockCount;
        }
    }

}
