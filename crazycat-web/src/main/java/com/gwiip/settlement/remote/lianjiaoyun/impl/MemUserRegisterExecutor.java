package com.gwiip.settlement.remote.lianjiaoyun.impl;


import com.gwiip.settlement.remote.lianjiaoyun.AbstractLianjiaoyunExecutor;
import com.gwiip.settlement.remote.lianjiaoyun.dto.MemUserRegisterRequest;
import com.gwiip.settlement.remote.lianjiaoyun.dto.MemUserRegisterResponse;
import com.gwiip.settlement.remote.lianjiaoyun.enums.ExchangeMemberStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MemUserRegisterExecutor
 * @Deacription TODO
 * @Author liyongbing
 * @Date 2020/5/29 13:44
 * @Version 1.0
 **/
@Component
public class MemUserRegisterExecutor extends AbstractLianjiaoyunExecutor<MemUserRegisterRequest, MemUserRegisterResponse> {
    @Override
    protected Map<String, String> preParam(MemUserRegisterRequest param) {
        Map<String,String> params = new HashMap<>();
        params.put("secret_key_info","");
        params.put("exchange_code",lianjiaoyunConfig.getExchange_code());//交易所代码	Y
        params.put("mem_code",lianjiaoyunConfig.getMem_code());//会员编号	Y
        params.put("exchange_fund_account",lianjiaoyunConfig.getExchange_fund_account());//资金账号	Y
        params.put("trade_account",lianjiaoyunConfig.getTrade_account());//交易账号	Y
        params.put("member_type", param.getMemberType().getValue());//会员类型	Y
        params.put("cust_type",param.getCustType().getValue());//客户类别	Y
        params.put("full_name",param.getFullName());//账户全称	Y		会员全称
        params.put("short_name","");//会员简称	N
        params.put("en_full_name","");//会员英文全称	N
        params.put("en_short_name","");//会员英文简称	N
        params.put("tel",param.getTel());//联系电话	Y		会员联系电话，手机或固定电话
        params.put("exchange_member_status", ExchangeMemberStatus.V_2.getValue());//交易所会员状态	Y
        params.put("up_mem_code",param.getUpMemCode());//关联会员账号	Y
        params.put("broker_code",param.getBrokerMemCode());//经纪会员编号	N		普通会员必填
        params.put("legal_repr",param.getLegalRepr());//法人代表	Y		机构必填项
        params.put("id_kind",param.getIdKind().getValue());//证件类别	Y
        params.put("id_no",param.getIdNo());//证件号码	Y		加密字段
        params.put("gender",param.getGender().getValue());//性别	Y
        params.put("nationality","0");//国籍地区	N
        params.put("business_licence","");//营业执照	N
        params.put("organ_code",param.getOrganCode());//组织机构代码	Y		机构必填
        params.put("tax_cert","");//税务登记证号	N
        params.put("reg_addr","");//注册地址	N
        params.put("com_addr","");//营业地址	N
        params.put("contact_name","");//经办人姓名	N		联系人姓名
        params.put("contact_tel","");//经办人电话	N		联系人电话
        params.put("contact_fax","");//联系人传真	N
        params.put("contact_email","");//经办人电子邮件	N		联系人邮箱
        return params;
    }

    @Override
    protected Class<MemUserRegisterResponse> getResultType() {
        return MemUserRegisterResponse.class;
    }

    @Override
    protected String getInterfaceName() {
        return "报送客户/会员开户信息";
    }

    @Override
    protected String getRequestMethod() {
        return lianjiaoyunConfig.getMemUserRegisterMethod();
    }
}
