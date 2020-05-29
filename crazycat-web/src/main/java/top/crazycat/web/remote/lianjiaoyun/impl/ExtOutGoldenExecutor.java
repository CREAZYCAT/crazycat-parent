package top.crazycat.web.remote.lianjiaoyun.impl;

import org.springframework.stereotype.Component;
import top.crazycat.web.remote.lianjiaoyun.AbstractLianjiaoyunExecutor;
import top.crazycat.web.remote.lianjiaoyun.dto.BaseResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExtInGoldenExecutor
 * @Deacription TODO
 * @Author 20030015
 * @Date 2020/5/29 18:12
 * @Version 1.0
 **/
@Component
public class ExtOutGoldenExecutor extends AbstractLianjiaoyunExecutor<String, BaseResponse> {
    @Override
    protected Map<String, String> preParam(String param) {
        Map<String,String> params = new HashMap<>();
        params.put("request_id","");//请求编号	Y		交易所流水号
        params.put("init_date","");//交易日期	Y		业务发生日期，YYYYMMDD
        params.put("exchange_code","");//交易所代码	Y
        params.put("exchange_fund_account","");//资金账号	Y
        params.put("fund_password","");//资金密码	N		加密字段，农行，浦发，民生，中行，兴业必填
        params.put("money_type","");//币种类别	Y
        params.put("bisin_type","");//银行业务类型	Y		只有重发，不做冲正
        params.put("bank_pro_code","");//银行产品代码	Y
        params.put("bank_account_name","");//银行户名	Y
        params.put("bank_account","");//银行账号	Y		加密字段
        params.put("occur_balance","");//发生金额	Y		单位：分，无小数点，最大长度为10位
        params.put("cross_flag","");//是否跨行	Y
        params.put("large_bank_id","");//大额行号	N
        params.put("union_bank_id","");//联行号	N
        params.put("out_acct_id_bank_name","");//出金账号开户行名	N		平安必填
        params.put("remark","");//备注	N
        params.put("busi_datetime","");//业务时间	Y		yyyyMMddHHmmss
        params.put("cust_type","");//客户类别	Y		会员主体类型
        params.put("full_name","");//账户全称	Y		会员全称，农行，工行，平安必填
        params.put("id_kind","");//证件类别	Y		平安，招行必填
        params.put("id_no","");//证件号码	Y		加密字段，平安，民生，招行必填
        params.put("out_poundage","");//出金手续费	Y		没有则传0
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
