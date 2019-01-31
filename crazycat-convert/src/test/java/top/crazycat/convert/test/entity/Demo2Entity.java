package top.crazycat.convert.test.entity;

import lombok.Data;
import top.crazycat.convert.annotation.ConvertTo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/18
 * description:
 */
@Data
@ConvertTo(Demo2Entity.class)
public class Demo2Entity extends DemoEntity {
    private static final long serialVersionUID = 4763127096351212712L;
    private String del;
}
