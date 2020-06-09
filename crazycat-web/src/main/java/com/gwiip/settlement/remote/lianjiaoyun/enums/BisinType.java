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
public enum BisinType {
    V_1("1","普通"),
    V_2("2","冲正"),
    V_3("3","重发"),
    V_4("4","调账"),
    ;

    private String value;
    private String desc;

}
