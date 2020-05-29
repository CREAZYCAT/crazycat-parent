package top.crazycat.web.remote.lianjiaoyun.impl;

import org.springframework.stereotype.Component;
import top.crazycat.web.remote.lianjiaoyun.AbstractLianjiaoyunExecutor;
import top.crazycat.web.remote.lianjiaoyun.dto.BaseResponse;
import top.crazycat.web.remote.lianjiaoyun.enums.RequestType;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OpenOrCloseExchangeExecutor
 * @Deacription TODO
 * @Author 20030015
 * @Date 2020/5/29 16:24
 * @Version 1.0
 **/
@Component
public class OpenOrCloseExchangeExecutor extends AbstractLianjiaoyunExecutor<String, BaseResponse> {
    @Override
    protected Map<String, String> preParam(String param) {
        Map<String,String> params = new HashMap<>();
        params.put("exchange_code",lianjiaoyunConfig.getExchange_code());//交易所代码	Y
        params.put("init_date","");//交易日期	Y 业务日期，yyyyMMdd
        params.put("bank_pro_code","");//银行产品代码	Y
        params.put("operating_status","");//营业状态	Y
        params.put("exchange_market_no","");//交易市场编号	N
        return params;
    }

    @Override
    protected Class<BaseResponse> getResultType() {
        return BaseResponse.class;
    }

    @Override
    protected String getInterfaceName() {
        return "银行签到/签退 ";
    }

    @Override
    protected String getRequestMethod() {
        return lianjiaoyunConfig.getOpenOrCloseExchangeMethod();
    }
}
