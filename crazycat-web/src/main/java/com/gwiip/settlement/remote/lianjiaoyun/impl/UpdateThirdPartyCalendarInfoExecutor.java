package com.gwiip.settlement.remote.lianjiaoyun.impl;

import com.gwiip.settlement.remote.lianjiaoyun.AbstractLianjiaoyunExecutor;
import com.gwiip.settlement.remote.lianjiaoyun.dto.BaseResponse;
import com.gwiip.settlement.remote.lianjiaoyun.dto.UpdateThirdPartyCalendarInfoRequest;
import com.gwiip.settlement.remote.lianjiaoyun.enums.BizType;
import com.gwiip.settlement.remote.lianjiaoyun.enums.ExchangeMarketType;
import com.gwiip.settlement.remote.lianjiaoyun.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UpdateThirdPartyCalendarInfoExecutor
 * @Deacription TODO
 * @Author liyongbing
 * @Date 2020/5/29 17:52
 * @Version 1.0
 **/
@Component
public class UpdateThirdPartyCalendarInfoExecutor extends AbstractLianjiaoyunExecutor<UpdateThirdPartyCalendarInfoRequest, BaseResponse> {
    @Override
    protected Map<String, String> preParam(UpdateThirdPartyCalendarInfoRequest param) {
        Map<String,String> params = new HashMap<>();
        params.put("init_date",param.getInitDate());//交易日期	Y		业务日期，YYYYMMDD
        params.put("exchange_code",lianjiaoyunConfig.getExchange_code());//交易所代码	Y
        params.put("exchange_market_type", ExchangeMarketType.V_1.getValue());//交易所市场编码	Y
        params.put("biz_type", BizType.V_1.getValue());//交易市场业务类型	Y
        params.put("close_flag",param.getCloseFlag().getValue());//开闭市标志	Y
        params.put("next_workday",param.getNextWorkDay());//下一工作日	Y
        params.put("busi_datetime", DateUtil.getCurrentDate_yyyyMMddHHmmss());//业务时间	Y		yyyyMMddHHmmss
        return params;
    }

    @Override
    protected Class<BaseResponse> getResultType() {
        return BaseResponse.class;
    }

    @Override
    protected String getInterfaceName() {
        return "发送开闭市通知";
    }

    @Override
    protected String getRequestMethod() {
        return lianjiaoyunConfig.getUpdateThirdPartyCalendarInfoMethod();
    }
}
