package com.gwiip.settlement.remote.lianjiaoyun.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName LianjiaoyunConfig
 * @Deacription TODO
 * @Author liyongbing
 * @Date 2020/5/29 11:59
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "remote.lianjiaoyun.config",ignoreInvalidFields = true)
@Data
public class LianjiaoyunConfig {
    //清算中心提供
    private String appKey = "36925ca9-6768-47f7-8124-fa2a9a8a2c7d";
    private String appSecret = "d392dff7-14f5-4437-a0b2-5386fa72162f";
    private String basic = "Basic ";
    private String exchange_code = "";
    private String baseUrl = "https://sandbox.hscloud.cn";

    //url path
    private String memUserRegisterMethod = "";
    private String openOrCloseExchangeMethod = "";
    private String updateThirdPartyCalendarInfoMethod = "";
    private String clientSignBindAccountMethod = "";
    private String extInGoldenMethod = "";
    private String extOutGoldenMethod = "";
    private String updateMainBankInfoMethod = "";
    private String fileUploadNotifyMethod = "";

    //交易所生成
    private String mem_code = "";
    private String exchange_fund_account = "";
    private String trade_account = "";



}
