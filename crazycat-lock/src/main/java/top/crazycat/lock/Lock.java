package top.crazycat.lock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/7/2
 * description:自定义lock接口
 */
public interface Lock {

    boolean lock(String lockKey, Long timeout);

    boolean lockDefault(String lockKey);

    void release(String lockKey);
}
