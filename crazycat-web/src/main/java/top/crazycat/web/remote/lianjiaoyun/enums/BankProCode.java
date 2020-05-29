package top.crazycat.web.remote.lianjiaoyun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName MemberType
 * @Deacription TODO
 * @Author 20030015
 * @Date 2020/5/29 15:21
 * @Version 1.0
 **/
@Getter
@AllArgsConstructor
public enum BankProCode {
    V_abcyst("abcyst","农业银行银商通二期"),
    V_bocnet("bocnet","中国银行银企对接"),
    V_bocspm("bocspm","交通银行银现转帐"),
    V_ccbsmt("ccbsmt","建设银行商贸通"),
    V_ceb("ceb","光大银行资金监管"),
    V_cgbcg("cgbcg","广发银行存管"),
    V_cibbce("cibbce","兴业银行银商三方存管"),
    V_citic("citic","中信银行大宗资管"),
    V_cmbyst("cmbyst","招商银行银商转帐"),
    V_cmsbsmt("cmsbsmt","民生银行银商"),
    V_hxbOtc("hxbOtc","华夏银行大宗三方存管平台"),
    V_icbcyz("icbcyz","工商银行银证转账"),
    V_jxbank("jxbank","江西银行"),
    V_pabankcg("pabankcg","平安银行存管"),
    V_sinapay("sinapay","新浪支付"),
    V_spdbcg("spdbcg","浦发银行存管"),
    V_boshzjjg("boshzjjg","上海银行"),
    V_tlzf("tlzf","通联支付"),
    V_boshone("boshone","上海银行一"),
    ;

    private String value;
    private String desc;

}
