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
public enum OperatingStatus {
    V_1("1","正常"),
    V_2("2","日终"),
    V_3("3","暂停"),
    ;

    private String value;
    private String desc;

}
