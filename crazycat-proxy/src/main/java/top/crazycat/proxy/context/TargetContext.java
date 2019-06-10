package top.crazycat.proxy.context;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:目标上下文，区分不同环境的实例
 */
public interface TargetContext {

    <T> T getTarget(Class<T> clazz);

}
