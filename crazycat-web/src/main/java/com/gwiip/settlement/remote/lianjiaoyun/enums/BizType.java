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
public enum BizType {
    V_1("1","不区分"),
    V_2("2","大宗商品"),
    V_3("3","金融资产"),
    V_4("4","文化产权"),
    V_5("5","邮币卡"),
    ;

    private String value;
    private String desc;

}
