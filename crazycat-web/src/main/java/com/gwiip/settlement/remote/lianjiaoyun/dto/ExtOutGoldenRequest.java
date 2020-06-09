package com.gwiip.settlement.remote.lianjiaoyun.dto;

import com.gwiip.settlement.remote.lianjiaoyun.enums.CrossFlag;
import com.gwiip.settlement.remote.lianjiaoyun.enums.CustType;
import lombok.Data;

/**
 * @ClassName ExtOutGoldenRequest
 * @Deacription
 * @Author liyongbing
 * @Date 2020/6/9 15:48
 * @Version 1.0
 **/
@Data
public class ExtOutGoldenRequest {
    private String requestId;
    private String initDate;
    private String bankAccountName;
    private String bankAccount;
    private Long occurBalance;//金额 分
    private CrossFlag crossFlag;
    private String remark;
    private CustType custType;
    private Long outPoundage;
}
