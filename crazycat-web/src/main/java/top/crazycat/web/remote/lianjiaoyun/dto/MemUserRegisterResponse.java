package top.crazycat.web.remote.lianjiaoyun.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName MemUserRegisterResponse
 * @Deacription TODO
 * @Author 20030015
 * @Date 2020/5/29 13:45
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MemUserRegisterResponse extends BaseResponse {
    private String secret_key_info;
    private String error_no;
    private String error_info;
    private String mem_code_clear;
    private String fund_account_clear;
    private String operator_no;
    private String op_password;

}
