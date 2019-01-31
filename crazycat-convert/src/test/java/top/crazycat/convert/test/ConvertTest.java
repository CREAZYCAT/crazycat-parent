package top.crazycat.convert.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import top.crazycat.convert.impl.DefaultConvertManager;
import top.crazycat.convert.processor.RegisterProcessor;
import top.crazycat.convert.test.entity.Demo2Entity;
import top.crazycat.convert.test.entity.Demo3Entity;
import top.crazycat.convert.test.entity.DemoEntity;
import top.crazycat.convert.util.ReflectUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/18
 * description:
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class ConvertTest {

    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        DefaultConvertManager manage = new DefaultConvertManager();
        manage.setScanPackages("top.crazycat.convert.test.entity");
        manage.setProcessor(new RegisterProcessor());
        manage.init();
        DemoEntity entity = new DemoEntity();
        entity.setCount(5);
        entity.setId(666L);
        entity.setSuccess(true);
        entity.setValue("ss");
        entity.setSale(BigDecimal.TEN);
        entity.setTime(new Date());
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(manage.convert(entity, Demo3Entity.class));
        System.out.println(System.currentTimeMillis()-start);
        manage.destroy();
    }

    @Test
    public void test2(){
        System.out.println(ReflectUtil.getClasses("top.crazycat.convert.test.entity"));
    }
}
