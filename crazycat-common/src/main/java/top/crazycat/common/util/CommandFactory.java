package top.crazycat.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/2/6
 * description:
 */
@Component
public class CommandFactory implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    public static  <T> T getBean(String beanName,Class<T> clazz){
        try {
            return context.getBean(beanName,clazz);
        } catch (BeansException e) {
            return null;
        }
    }

    public static  <T> T getBean(Class<T> clazz){
        try {
            return context.getBean(clazz);
        } catch (BeansException e) {
            return null;
        }
    }
    public static Object getBean(String beanName){
        try {
            return context.getBean(beanName);
        } catch (BeansException e) {
            return null;
        }
    }
}
