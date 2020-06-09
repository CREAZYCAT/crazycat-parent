package com.gwiip.settlement.remote.lianjiaoyun.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Deacription
 * @Author liyongbing
 * @Date 2020/6/9 13:55
 * @Version 1.0
 **/
public class DateUtil {


    public static String getCurrentDate_yyyyMMddHHmmss(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }
}
