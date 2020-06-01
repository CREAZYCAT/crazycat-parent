package com.gwiip.settlement.remote.lianjiaoyun.impl;

import com.gwiip.settlement.remote.lianjiaoyun.AbstractLianjiaoyunExecutor;
import com.gwiip.settlement.remote.lianjiaoyun.dto.BaseResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UpdateMainBankInfoExecutor extends AbstractLianjiaoyunExecutor<String, BaseResponse> {
	@Override
	protected Map<String, String> preParam(String param) {
		Map<String,String> params = new HashMap<>();
		params.put("mobile_tel","");//手机号码	N		上海银行必输
		params.put("id_no","");//证件号码	N		上海银行必输
		params.put("id_kind","");//证件类别	N		上海银行必输
		params.put("open_bank_no","");//银行卡的开户行号	N		上海银行必输
		params.put("secret_key_info","");//密钥信息	Y
		params.put("init_date","");//交易日期	Y		业务发生日期，YYYYMMDD
		params.put("request_id","");//请求编号	Y		交易所流水号
		params.put("exchange_code","");//交易所代码	Y
		params.put("exchange_fund_account","");//资金账号	Y
		params.put("bank_pro_code","");//银行产品代码	Y
		params.put("bank_account","");//银行账号	Y		加密字段
		params.put("bank_account_name","");//银行户名	N		上海银行必输
		params.put("bank_password","");//银行密码	N		加密字段
		params.put("full_name","");//账户全称	Y		会员全称，仅允许修改full_name
		return params;
	}

	@Override
	protected Class<BaseResponse> getResultType() {
		return BaseResponse.class;
	}

	@Override
	protected String getInterfaceName() {
		return "客户银行信息变更";
	}

	@Override
	protected String getRequestMethod() {
		return lianjiaoyunConfig.getUpdateMainBankInfoMethod();
	}
}
