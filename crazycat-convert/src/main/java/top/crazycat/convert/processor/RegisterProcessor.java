package top.crazycat.convert.processor;

import top.crazycat.convert.ConvertManager;
import top.crazycat.convert.annotation.ConvertTo;
import top.crazycat.convert.annotation.multipleSupport.ConvertTos;
import top.crazycat.convert.util.ListUtil;
import top.crazycat.convert.util.ReflectUtil;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/18
 * description:注册的注解处理，自动注册到当前实例
 */
public class RegisterProcessor implements ConvertProcessor {

    public void execute(ConvertManager manager) {
        String[] packages = manager.scanPackages();
        if(packages.length>0){
            for(String p : packages){
                Set<Class<?>> classes = ReflectUtil.getClasses(p);
                if(ListUtil.isNotEmpty(classes)){
                    for(Class clazz:classes){
                        Annotation[] annotations = clazz.getAnnotationsByType(ConvertTo.class);
                        for(Annotation annotation:annotations){
                            if(annotation instanceof ConvertTo){
                                ConvertTo convertTo = (ConvertTo) annotation;
                                manager.register(clazz,convertTo.value(),convertTo.openDateConvert()?convertTo.datePattern():null);
                            }
                        }
                    }
                }
            }
        }
    }

}
