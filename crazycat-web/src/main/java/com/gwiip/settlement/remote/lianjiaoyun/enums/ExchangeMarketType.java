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
public enum ExchangeMarketType {
    V_1("1","不区分"),
    V_2("2","做市交易"),
    V_3("3","挂牌交易"),
    V_4("4","电子商城"),
    ;

    private String value;
    private String desc;

}
