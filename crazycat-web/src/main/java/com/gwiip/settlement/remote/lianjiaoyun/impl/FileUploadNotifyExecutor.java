package com.gwiip.settlement.remote.lianjiaoyun.impl;

import com.gwiip.settlement.remote.lianjiaoyun.AbstractLianjiaoyunExecutor;
import com.gwiip.settlement.remote.lianjiaoyun.dto.BaseResponse;
import com.gwiip.settlement.remote.lianjiaoyun.dto.FileUploadNotifyRequest;
import com.gwiip.settlement.remote.lianjiaoyun.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FileUploadNotifyExecutor extends AbstractLianjiaoyunExecutor<FileUploadNotifyRequest, BaseResponse> {
	@Override
	protected Map<String, String> preParam(FileUploadNotifyRequest param) {
		Map<String,String> params = new HashMap<>();
		params.put("init_date",param.getInitDate());//交易日期	Y		YYYYMMDD
		params.put("exchange_code",lianjiaoyunConfig.getExchange_code());//交易所代码	Y
		params.put("file_type",param.getFileType().getValue());//文件类别	Y
		params.put("file_path",param.getFilePath());//影像存储路径	Y
		params.put("file_name",param.getFileName());//文件名称	Y
		params.put("resend",param.getResend());//重发标志	Y		0:否，1：是
		params.put("file_md5",param.getFileMd5());//文件md5码	Y
		params.put("busi_datetime", DateUtil.getCurrentDate_yyyyMMddHHmmss());//业务时间	Y		yyyyMMddHHmmss
		return params;
	}

	@Override
	protected Class<BaseResponse> getResultType() {
		return BaseResponse.class;
	}

	@Override
	protected String getInterfaceName() {
		return "交易所报送文件通知";
	}

	@Override
	protected String getRequestMethod() {
		return lianjiaoyunConfig.getFileUploadNotifyMethod();
	}
}
