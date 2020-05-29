package top.crazycat.web.remote.lianjiaoyun.dto;

import lombok.Data;

/**
 * @ClassName BaseResponse
 * @Deacription TODO
 * @Author 20030015
 * @Date 2020/5/29 16:15
 * @Version 1.0
 **/
@Data
public class BaseResponse {
//{"data":[{"error_info":"操作成功","error_no":"0","serial_no":"  ","trade_serial_no":"   "}]}

    private String error_no;
    private String error_info;
    private String serial_no;
    private String trade_serial_no;
}
