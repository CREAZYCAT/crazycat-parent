package com.gwiip.settlement.remote.lianjiaoyun.dto;

import com.gwiip.settlement.remote.lianjiaoyun.enums.FileType;
import lombok.Data;

/**
 * @ClassName FileUploadNotifyRequest
 * @Deacription
 * @Author liyongbing
 * @Date 2020/6/9 14:54
 * @Version 1.0
 **/
@Data
public class FileUploadNotifyRequest {
    private String initDate;
    private FileType fileType;
    private String filePath;
    private String fileName;
    private String resend;
    private String fileMd5;
}
