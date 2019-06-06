package top.crazycat.proxy.context;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
public interface TargetContext {

    <T> T getTarget(Class<T> clazz);

}
