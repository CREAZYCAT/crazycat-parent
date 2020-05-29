package top.crazycat.web.remote.lianjiaoyun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName MemberType
 * @Deacription TODO
 * @Author 20030015
 * @Date 2020/5/29 15:21
 * @Version 1.0
 **/
@Getter
@AllArgsConstructor
public enum MemberType {
    V_1("1","交易所"),
    V_2("2","综合类"),
    V_3("3","结算类"),
    V_4("4","经纪类"),
    V_5("5","交易类"),
    V_6("6","产业类"),
    V_7("7","服务类"),
    V_8("8","其他"),
    ;

    private String value;
    private String desc;

}
