package com.gwiip.settlement.remote.lianjiaoyun.impl;

import com.gwiip.settlement.remote.lianjiaoyun.AbstractLianjiaoyunExecutor;
import com.gwiip.settlement.remote.lianjiaoyun.dto.ClientSignBindAccountRequest;
import com.gwiip.settlement.remote.lianjiaoyun.dto.ClientSignBindAccountResponse;
import com.gwiip.settlement.remote.lianjiaoyun.enums.BankProCode;
import com.gwiip.settlement.remote.lianjiaoyun.enums.MoneyType;
import com.gwiip.settlement.remote.lianjiaoyun.enums.SignType;
import com.gwiip.settlement.remote.lianjiaoyun.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ClientSignBindAccountExecutor
 * @Deacription TODO
 * @Author liyongbing
 * @Date 2020/5/29 18:03
 * @Version 1.0
 **/
@Component
public class ClientSignBindAccountExecutor extends AbstractLianjiaoyunExecutor<ClientSignBindAccountRequest, ClientSignBindAccountResponse> {
    @Override
    protected Map<String, String> preParam(ClientSignBindAccountRequest param) {
        Map<String,String> params = new HashMap<>();
        params.put("secret_key_info","");//密钥信息	Y
        params.put("init_date", param.getInitDate());//交易日期	Y		业务发生日期，YYYYMMDD
        params.put("request_id",param.getRequestId());//请求编号	Y		交易所流水号
        params.put("exchange_code",lianjiaoyunConfig.getExchange_code());//交易所代码	Y
        params.put("exchange_fund_account",lianjiaoyunConfig.getExchange_fund_account());//资金账号	Y
        params.put("bank_pro_code", BankProCode.V_boshzjjg.getValue());//银行产品代码	Y
        params.put("bank_account",param.getBankAccount());//银行账号	Y		加密字段；上海银行预签约必输
        params.put("bank_account_name",param.getBankAccountName());//银行户名	Y
        params.put("fund_password","");//资金密码	N		加密字段，上海银行预、通联支付不填
        params.put("money_type", MoneyType.V_0.getValue());//币种类别	Y
        params.put("busi_datetime",DateUtil.getCurrentDate_yyyyMMddHHmmss());//业务时间	Y		yyyyMMddHHmmss
        params.put("cust_type",param.getCustType().getValue());//客户类别	Y		会员主体类型
        params.put("full_name",param.getFullName());//账户全称	Y		会员全称
        params.put("id_kind",param.getIdKind().getValue());//证件类别	Y
        params.put("id_no",param.getIdNo());//证件号码	Y		加密字段
        params.put("sign_type", param.getSignType().getValue());//签约类型	Y
        params.put("open_bank_no",param.getOpenBankNo());//银行卡的开户行号	N		民生市场通机构签约时必输；上海银行预签约必输；通联支付建议填写
        params.put("cmbc_city_no","");//开户行所在的市编号	N		民生市场通机构签约时必输
        params.put("open_branch_no","");//开户营业部	N		"大额支付行名称
        params.put("sign_flag","");//民生市场通机构签约时必输
        params.put("mobile_tel","");//根据接口【112123】查询"
        params.put("ticket_key","");//签约标志	N		00-协议签约
        return params;
    }

    @Override
    protected Class<ClientSignBindAccountResponse> getResultType() {
        return ClientSignBindAccountResponse.class;
    }

    @Override
    protected String getInterfaceName() {
        return "客户与银行签约解约";
    }

    @Override
    protected String getRequestMethod() {
        return lianjiaoyunConfig.getClientSignBindAccountMethod();
    }
}
