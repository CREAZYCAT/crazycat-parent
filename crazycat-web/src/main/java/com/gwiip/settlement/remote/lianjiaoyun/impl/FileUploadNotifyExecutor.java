package com.gwiip.settlement.remote.lianjiaoyun.impl;

import com.gwiip.settlement.remote.lianjiaoyun.AbstractLianjiaoyunExecutor;
import com.gwiip.settlement.remote.lianjiaoyun.dto.BaseResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FileUploadNotifyExecutor extends AbstractLianjiaoyunExecutor<String, BaseResponse> {
	@Override
	protected Map<String, String> preParam(String param) {
		Map<String,String> params = new HashMap<>();
		params.put("init_date","");//交易日期	Y		YYYYMMDD
		params.put("exchange_code","");//交易所代码	Y
		params.put("file_type","");//文件类别	Y
		params.put("file_path","");//影像存储路径	Y
		params.put("file_name","");//文件名称	Y
		params.put("resend","");//重发标志	Y		0:否，1：是
		params.put("file_md5","");//文件md5码	Y
		params.put("busi_datetime","");//业务时间	Y		yyyyMMddHHmmss
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
