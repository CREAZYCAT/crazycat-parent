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
public enum IDKind {
    V_0("0","身份证"),
    V_1("1","外国护照"),
    V_2("2","营业执照"),
    V_3("3","军官证"),
    V_4("4","社会保障号"),
    V_7("7","行政机关"),
    V_9("9","法人社团"),
    V_a("a","非法人社团"),
    V_b("b","军队"),
    V_c("c","武警"),
    V_C("C","解放军文职干部证"),
    V_d("d","下属机构"),
    V_D("D","警官证"),
    V_E("E","解放军士兵证"),
    V_F("F","户口簿"),
    V_f("f","基金会"),
    V_G("G","港澳居民来往内地通行证"),
    V_g("g","技术监督局"),
    V_h("h","回乡证"),
    V_H("H","台湾居民来往大陆通行证"),
    V_I("I","外国人永久居留证"),
    V_J("J","本国护照"),
    V_K("K","武警文职干部证"),
    V_L("L","武警士兵证"),
    V_M("M","社会团体"),
    V_N("N","临时身份证"),
    V_O("O","驾驶证"),
    V_P("P","全国组织机构代码"),
    V_Q("Q","海外客户编号"),
    V_R("R","境外身份证"),
    V_S("S","港澳台居民身份证"),
    V_T("T","事业法人登记证书"),
    V_X("X","其他有效证件"),
    V_Y("Y","资管产品备案函"),
    V_z("z","社会信用代码"),
    ;

    private String value;
    private String desc;

}
