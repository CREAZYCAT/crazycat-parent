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
public enum FileType {
    V_I1("I1","客户信息变更文件"),
    V_I2("I2","成交数据文件"),
    V_I3("I3","资金余额文件"),
    V_I4("I4","成交清算文件"),
    V_I5("I5","持仓汇总文件"),
    V_I6("I6","持仓明细文件"),
    V_I7("I7","出入金流水文件"),
    V_I8("I8","账户类对账明细文件"),
    V_I9("I9","外部费用明细文件"),
    V_IA("IA","行情文件"),
    V_IB("IB","结算价文件"),
    V_IC("IC","交割单文件"),
    V_ID("ID","限价单文件"),
    V_IE("IE","产品信息同步文件"),
    ;

    private String value;
    private String desc;

}
