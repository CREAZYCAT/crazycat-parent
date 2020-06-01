package com.gwiip.settlement.remote.lianjiaoyun.dto;

import com.gwiip.settlement.remote.lianjiaoyun.enums.CustType;
import com.gwiip.settlement.remote.lianjiaoyun.enums.MemberType;
import lombok.Data;

/**
 * @ClassName MemUserRegisterRequest
 * @Deacription TODO
 * @Author liyongbing
 * @Date 2020/5/29 13:45
 * @Version 1.0
 **/
@Data
public class MemUserRegisterRequest {
	private MemberType memberType;
	private CustType custType;
}
