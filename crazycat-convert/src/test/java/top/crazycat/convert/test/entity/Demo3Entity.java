package top.crazycat.convert.test.entity;

import lombok.Data;
import top.crazycat.convert.annotation.ConvertTo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/22
 * description:
 */
@ConvertTo(value = DemoEntity.class,openDateConvert = true)
@Data
public class Demo3Entity implements Serializable {
    private static final long serialVersionUID = -6485339782953828381L;
    private BigDecimal sale;
    private Boolean success;
    private String time;
}
