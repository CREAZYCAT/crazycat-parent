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
public enum CloseFlag {
    V_0("0","闭市"),
    V_1("1","开市"),
    ;

    private String value;
    private String desc;

}
