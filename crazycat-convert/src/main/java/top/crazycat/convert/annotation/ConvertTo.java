package top.crazycat.convert.annotation;

import top.crazycat.convert.annotation.multipleSupport.ConvertTos;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/18
 * description:
 */
@Repeatable(value = ConvertTos.class)
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertTo {
    Class value();
    boolean openDateConvert() default false;
    String datePattern() default "yyyy-MM-dd HH:mm:ss";

}
