package com.gwiip.settlement.remote.lianjiaoyun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName MemberType
 * @Deacription TODO
 * @Author liyongbing
 * @Date 2020/5/29 15:21
 * @Version 1.0
 **/
@Getter
@AllArgsConstructor
public enum SignType {
    V_1("1","签约"),
    V_2("2","解约"),
    V_3("3","强签"),
    V_4("4","强解"),
    V_5("5","预签约"),
    V_6("6","创建资金账户"),
    V_7("7","关闭资金账户"),
    ;

    private String value;
    private String desc;

}
