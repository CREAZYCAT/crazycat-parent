package com.gwiip.settlement.remote.lianjiaoyun.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName ClientSignBindAccountResponse
 * @Deacription TODO
 * @Author liyongbing
 * @Date 2020/5/29 18:06
 * @Version 1.0
 **/

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientSignBindAccountResponse extends BaseResponse {
    private String ticket_key;
}
