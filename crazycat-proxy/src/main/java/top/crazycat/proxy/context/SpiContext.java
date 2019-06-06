package top.crazycat.proxy.context;

import java.util.Objects;
import java.util.ServiceLoader;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
public class SpiContext implements TargetContext {
    @Override
    public <T> T getTarget(Class<T> clazz) {
        Objects.requireNonNull(clazz,"no class input");
        ServiceLoader<T> loaders = ServiceLoader.load(clazz);
        if(loaders.iterator().hasNext()){
            return loaders.iterator().next();
        }
        return null;
    }
}
