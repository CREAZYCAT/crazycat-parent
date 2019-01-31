package top.crazycat.convert.test.entity;

import lombok.Data;
import top.crazycat.convert.annotation.ConvertTo;
import top.crazycat.convert.annotation.multipleSupport.ConvertTos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/16
 * description:
 */
@ConvertTo(value = Demo3Entity.class,openDateConvert = true)
@ConvertTo(DemoEntity.class)
@Data
public class DemoEntity implements Serializable {
    private static final long serialVersionUID = 7892100636206026104L;
    private String value;
    private Integer count;
    private Long id;
    private BigDecimal sale;
    private Boolean success;
    private Date time;

}
