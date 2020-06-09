package com.gwiip.settlement.remote.lianjiaoyun.dto;

import com.gwiip.settlement.remote.lianjiaoyun.enums.ClientGender;
import com.gwiip.settlement.remote.lianjiaoyun.enums.CustType;
import com.gwiip.settlement.remote.lianjiaoyun.enums.IDKind;
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
	private String fullName;
	private String tel;
	private String upMemCode;
	private String brokerMemCode;
	private String legalRepr;
	private IDKind idKind;
	private String idNo;
	private ClientGender gender;
	private String organCode;
}
