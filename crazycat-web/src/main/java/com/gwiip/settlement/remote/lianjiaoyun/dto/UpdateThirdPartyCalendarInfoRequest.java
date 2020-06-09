package com.gwiip.settlement.remote.lianjiaoyun.dto;

import com.gwiip.settlement.remote.lianjiaoyun.enums.CloseFlag;
import lombok.Data;

/**
 * @ClassName UpdateThirdPartyCalendarInfoRequest
 * @Deacription
 * @Author liyongbing
 * @Date 2020/6/9 14:26
 * @Version 1.0
 **/
@Data
public class UpdateThirdPartyCalendarInfoRequest {
    private String initDate;
    private CloseFlag closeFlag;
    private String nextWorkDay;
}
