package com.gwiip.settlement.remote.lianjiaoyun.dto;

import com.gwiip.settlement.remote.lianjiaoyun.enums.IDKind;
import lombok.Data;

/**
 * @ClassName UpdateMainBankInfoRequest
 * @Deacription
 * @Author liyongbing
 * @Date 2020/6/9 14:39
 * @Version 1.0
 **/
@Data
public class UpdateMainBankInfoRequest {
    private String mobileTel;
    private String idNo;
    private IDKind idKind;
    private String openBankNo;
    private String initDate;
    private String requestId;
    private String bankAccount;
    private String bankAccountName;
    private String fullName;
}
