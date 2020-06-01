package com.gwiip.settlement.remote.lianjiaoyun.impl;

import com.gwiip.settlement.remote.lianjiaoyun.AbstractLianjiaoyunExecutor;
import com.gwiip.settlement.remote.lianjiaoyun.dto.ClientSignBindAccountResponse;
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
public class ClientSignBindAccountExecutor extends AbstractLianjiaoyunExecutor<String, ClientSignBindAccountResponse> {
    @Override
    protected Map<String, String> preParam(String param) {
        Map<String,String> params = new HashMap<>();
        params.put("secret_key_info","");//密钥信息	Y
        params.put("init_date","");//交易日期	Y		业务发生日期，YYYYMMDD
        params.put("request_id","");//请求编号	Y		交易所流水号
        params.put("exchange_code","");//交易所代码	Y
        params.put("exchange_fund_account","");//资金账号	Y
        params.put("bank_pro_code","");//银行产品代码	Y
        params.put("bank_account","");//银行账号	Y		加密字段；上海银行预签约必输
        params.put("bank_account_name","");//银行户名	Y
        params.put("fund_password","");//资金密码	N		加密字段，上海银行预、通联支付不填
        params.put("money_type","");//币种类别	Y
        params.put("busi_datetime","");//业务时间	Y		yyyyMMddHHmmss
        params.put("cust_type","");//客户类别	Y		会员主体类型
        params.put("full_name","");//账户全称	Y		会员全称
        params.put("id_kind","");//证件类别	Y
        params.put("id_no","");//证件号码	Y		加密字段
        params.put("sign_type","");//签约类型	Y
        params.put("open_bank_no","");//银行卡的开户行号	N		民生市场通机构签约时必输；上海银行预签约必输；通联支付建议填写
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
