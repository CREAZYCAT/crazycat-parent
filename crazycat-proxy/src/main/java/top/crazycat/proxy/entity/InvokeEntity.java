package top.crazycat.proxy.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/6
 * description:调用记录实体
 */
@Data
public class InvokeEntity implements Serializable {
    private String clazz;
    private String method;
    private String args;
    private String result;
    private Long executeTime;
    private Integer exceptionFlag;
    private String exceptionMessage;
}
