package top.crazycat.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/1/16
 * description:
 */
@Data
public class DemoEntity implements Serializable {
    private static final long serialVersionUID = 7892100636206026104L;
    private String value;
    private Integer count;
    private Long id;
    private BigDecimal sale;
    private Boolean success;

}
