package com.gwiip.settlement.remote.lianjiaoyun.dto;

import com.gwiip.settlement.remote.lianjiaoyun.enums.OperatingStatus;
import lombok.Data;

/**
 * @ClassName OpenOrCloseExchangeRequest
 * @Deacription
 * @Author liyongbing
 * @Date 2020/6/9 14:16
 * @Version 1.0
 **/
@Data
public class OpenOrCloseExchangeRequest {
    private String initDate;
    private OperatingStatus operatingStatus;
}
