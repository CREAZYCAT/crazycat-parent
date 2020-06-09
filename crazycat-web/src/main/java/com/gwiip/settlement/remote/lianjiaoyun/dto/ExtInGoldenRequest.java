package com.gwiip.settlement.remote.lianjiaoyun.dto;

import com.gwiip.settlement.remote.lianjiaoyun.enums.CustType;
import lombok.Data;

/**
 * @ClassName ExtInGoldenRequest
 * @Deacription
 * @Author liyongbing
 * @Date 2020/6/9 15:48
 * @Version 1.0
 **/
@Data
public class ExtInGoldenRequest {
    private String requestId;
    private String initDate;
    private String bankAccount;
    private Long occurBalance;//金额 分
    private String remark;
    private CustType custType;
}
