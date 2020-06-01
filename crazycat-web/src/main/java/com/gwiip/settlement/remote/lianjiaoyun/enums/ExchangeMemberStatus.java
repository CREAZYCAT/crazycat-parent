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
public enum ExchangeMemberStatus {
    V_1("1","已签约"),
    V_2("2","未签约"),
    V_3("3","销户"),
    ;

    private String value;
    private String desc;

}
