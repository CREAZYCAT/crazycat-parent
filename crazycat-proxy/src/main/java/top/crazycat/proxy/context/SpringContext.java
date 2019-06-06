package top.crazycat.proxy.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:
 */
@Component
public class SpringContext implements TargetContext,ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public <T> T getTarget(Class<T> clazz) {
        try {
            return context.getBean(clazz);
        } catch (Exception e) {
            return null;
        }
    }
}
