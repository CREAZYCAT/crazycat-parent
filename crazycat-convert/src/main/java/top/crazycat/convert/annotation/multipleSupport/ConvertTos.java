package top.crazycat.convert.annotation.multipleSupport;


import top.crazycat.convert.annotation.ConvertTo;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/21
 * description:
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConvertTos {
    ConvertTo[] value() default {};
}
