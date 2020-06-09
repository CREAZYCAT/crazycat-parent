package com.gwiip.settlement.remote.lianjiaoyun.dto;

import com.gwiip.settlement.remote.lianjiaoyun.enums.CustType;
import com.gwiip.settlement.remote.lianjiaoyun.enums.IDKind;
import com.gwiip.settlement.remote.lianjiaoyun.enums.SignType;
import lombok.Data;

/**
 * @ClassName ClientSignBindAccountRequest
 * @Deacription
 * @Author liyongbing
 * @Date 2020/6/9 13:58
 * @Version 1.0
 **/
@Data
public class ClientSignBindAccountRequest {
    private String requestId;
    private String bankAccount;
    private String bankAccountName;
    private CustType custType;
    private String fullName;
    private IDKind idKind;
    private String idNo;
    private SignType signType;
    private String openBankNo;
    private String initDate;
}
