package top.crazycat.web.remote.lianjiaoyun;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.crazycat.web.remote.Executor;
import top.crazycat.web.remote.lianjiaoyun.config.LianjiaoyunConfig;
import top.crazycat.web.remote.lianjiaoyun.dto.AccessToken;
import top.crazycat.web.remote.lianjiaoyun.enums.RequestType;
import top.crazycat.web.remote.lianjiaoyun.util.HttpClientUtil;
import top.crazycat.web.remote.lianjiaoyun.util.JacksonUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AbstractLianjiaoyunExecutor
 * @Deacription TODO
 * @Author 20030015
 * @Date 2020/5/29 11:57
 * @Version 1.0
 **/
@Slf4j
@Component
public abstract class AbstractLianjiaoyunExecutor<P,R> implements Executor<P,R> {

    @Autowired
    protected LianjiaoyunConfig lianjiaoyunConfig;


    @Override
    public R execute(P param) {
        try {
            AccessToken token = getToken();
            Map<String, String> requestParam = preParam(param);
            RequestType type = getRequestType();
            String requestMethod = getRequestMethod();
            String resultBody;
            if (RequestType.GET.equals(type)) {
                resultBody = HttpClientUtil.sendGet(lianjiaoyunConfig.getBaseUrl() + requestMethod, requestParam, HttpClientUtil.CHARSET,
                        null, HttpClientUtil.BEARER + token.getAccess_token(), getInterfaceName());
            }else {
                resultBody = HttpClientUtil.sendPost(lianjiaoyunConfig.getBaseUrl() + requestMethod, requestParam, HttpClientUtil.CHARSET,
                        HttpClientUtil.CHARSET, null, HttpClientUtil.BEARER + token.getAccess_token(), getInterfaceName());
            }
            if (StringUtils.isEmpty(resultBody)) {
                throw new IllegalAccessException("no result found");
            }
            resultBody = resultBody.replace("{\"data\":[", "").replace("]}", "");
            return JacksonUtils.json2Object(resultBody,getResultType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    protected abstract Map<String, String> preParam(P param);

    protected abstract Class<R> getResultType();

    protected abstract String getInterfaceName();

    protected abstract String getRequestMethod();

    protected RequestType getRequestType(){
        return RequestType.POST;
    }



    private AccessToken getToken() throws UnsupportedEncodingException {
        // 客户端凭证模式 获取令牌
        Map<String, String> token_map = new HashMap<String, String>();
        token_map.put("grant_type", "client_credentials");// 客户端凭证模式时，必须为“client_credentials”；
        String tokenResult = HttpClientUtil.sendPost(lianjiaoyunConfig.getBaseUrl() + "/oauth2/oauth2/token", token_map,
                HttpClientUtil.CHARSET, HttpClientUtil.CHARSET, null, HttpClientUtil.Base64(lianjiaoyunConfig.getAppKey(), lianjiaoyunConfig.getAppSecret(), lianjiaoyunConfig.getBasic()),
                "获取公共令牌");
        // 解析返回数据json
        return JacksonUtils.json2Object(tokenResult, AccessToken.class);
    }
}
