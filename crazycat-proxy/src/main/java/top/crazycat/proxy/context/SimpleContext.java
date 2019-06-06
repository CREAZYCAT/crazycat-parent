package top.crazycat.proxy.context;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
public class SimpleContext implements TargetContext {
    @Override
    public <T> T getTarget(Class<T> clazz) {
        Objects.requireNonNull(clazz,"no class input");
        if(clazz.isInterface()){
            return null;
        }
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
