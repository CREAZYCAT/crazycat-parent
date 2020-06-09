package com.gwiip.settlement.remote.lianjiaoyun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName CustType
 * @Deacription TODO
 * @Author liyongbing
 * @Date 2020/5/29 15:28
 * @Version 1.0
 **/

@Getter
@AllArgsConstructor
public enum CrossFlag {
    V_0("0","不跨行"),
    V_1("1","跨行"),
    ;
    private String value;
    private String desc;
}
