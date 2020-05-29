package top.crazycat.web.remote.lianjiaoyun.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName CustType
 * @Deacription TODO
 * @Author 20030015
 * @Date 2020/5/29 15:28
 * @Version 1.0
 **/

@Getter
@AllArgsConstructor
public enum CustType {
    V_0("0","个人"),//0	个人
    V_1("1","机构"),//1	机构
    ;
    private String value;
    private String desc;
}
