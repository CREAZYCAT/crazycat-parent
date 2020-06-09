package com.gwiip.settlement.remote.lianjiaoyun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName MoneyType
 * @Deacription TODO
 * @Author liyongbing
 * @Date 2020/5/29 15:21
 * @Version 1.0
 **/
@Getter
@AllArgsConstructor
public enum MoneyType {
    V_0("0","人民币"),
    V_1("1","美  元"),
    V_2("2","港  币"),
    V_3("3","SZ-HKD"),
    V_6("6","AUD"),
    V_E("E","CAD"),
    V_f("f","MYR"),
    V_k("k","丹麦克朗"),
    V_l("l","PHP"),
    V_M("M","EUR"),
    V_o("o","澳门元"),
    V_Q("Q","GBP"),
    V_r("r","瑞典克朗"),
    V_S("S","IDR"),
    V_t("t","SGD"),
    V_v("v","THB"),
    V_W("W","JPY"),
    V_w("w","TWD"),
    ;

    private String value;
    private String desc;

}
