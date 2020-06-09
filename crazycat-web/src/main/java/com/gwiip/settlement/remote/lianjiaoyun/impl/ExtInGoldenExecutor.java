package com.gwiip.settlement.remote.lianjiaoyun.impl;

import com.gwiip.settlement.remote.lianjiaoyun.AbstractLianjiaoyunExecutor;
import com.gwiip.settlement.remote.lianjiaoyun.dto.BaseResponse;
import com.gwiip.settlement.remote.lianjiaoyun.dto.ExtInGoldenRequest;
import com.gwiip.settlement.remote.lianjiaoyun.enums.BankProCode;
import com.gwiip.settlement.remote.lianjiaoyun.enums.BisinType;
import com.gwiip.settlement.remote.lianjiaoyun.enums.MoneyType;
import com.gwiip.settlement.remote.lianjiaoyun.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExtInGoldenExecutor
 * @Deacription TODO
 * @Author liyongbing
 * @Date 2020/5/29 18:12
 * @Version 1.0
 **/
@Component
public class ExtInGoldenExecutor extends AbstractLianjiaoyunExecutor<ExtInGoldenRequest, BaseResponse> {
    @Override
    protected Map<String, String> preParam(ExtInGoldenRequest param) {
        Map<String,String> params = new HashMap<>();
        params.put("secret_key_info","");//密钥信息	Y
        params.put("request_id",param.getRequestId());//请求编号	Y		交易所流水号
        params.put("init_date",param.getInitDate());//交易日期	Y		业务发生日期，YYYYMMDD
        params.put("exchange_code",lianjiaoyunConfig.getExchange_code());//交易所代码	Y
        params.put("exchange_fund_account",lianjiaoyunConfig.getExchange_fund_account());//资金账号	Y
        params.put("fund_password","");//资金密码	N		加密字段，农行，浦发，民生，中行，兴业必填
        params.put("money_type", MoneyType.V_0.getValue());//币种类别	Y
        params.put("bisin_type", BisinType.V_1.getValue());//银行业务类型	Y
        params.put("bank_pro_code", BankProCode.V_boshzjjg.getValue());//银行产品代码	Y
        params.put("bank_account_name","");//银行户名	N
        params.put("bank_account",param.getBankAccount());//银行账号	Y		加密字段
        params.put("occur_balance", String.valueOf(param.getOccurBalance()));//发生金额	Y		单位：分，无小数点，最大长度为10位
        params.put("remark",param.getRemark());//备注	N
        params.put("busi_datetime", DateUtil.getCurrentDate_yyyyMMddHHmmss());//业务时间	Y		yyyyMMddHHmmss
        params.put("cust_type",param.getCustType().getValue());//客户类别	Y		会员主体类型
        params.put("full_name","");//账户全称	Y		会员全称，农行，工行，平安，民生必填
        params.put("id_kind","");//证件类别	Y		平安，招行必填
        params.put("id_no","");//证件号码	Y		加密字段，平安，民生，招行必填
        return params;
    }

    @Override
    protected Class<BaseResponse> getResultType() {
        return BaseResponse.class;
    }

    @Override
    protected String getInterfaceName() {
        return "客户入金";
    }

    @Override
    protected String getRequestMethod() {
        return lianjiaoyunConfig.getExtInGoldenMethod();
    }
}
