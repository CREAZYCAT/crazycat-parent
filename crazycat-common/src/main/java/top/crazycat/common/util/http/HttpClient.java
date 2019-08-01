package top.crazycat.common.util.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/18
 * description:HttpClient，参数统一string,form表单交互可用HttpClientUtil#getUrlParamsByMap()转换，异常由外部调用者处理
 */
public class HttpClient implements RemoteClient {
    private final static int TIMEOUT_DEFAULT = 5000;
    private boolean success;
    private HttpMethod method;
    private int timeout;
    private Map<String, String> headers = new HashMap<>();
    private String url;
    private String charset;
    private String params;
    private Throwable throwable;
    private String errorMsg;
    private byte[] result;
    private Class<?> returnType;
    private MessageConvert messageConvert;

    public HttpClient() {
        init(String.class);
    }

    public HttpClient(Class type) {
        init(type);
    }

    private void init(Class type) {
        success = false;
        method = HttpMethod.GET;
        timeout = TIMEOUT_DEFAULT;
        charset = "UTF-8";
        params = "";
        addHeader("Content-Type", "application/x-www-form-urlencoded");
        returnType = type;
        messageConvert = MessageConvert.JSON_CONVERT;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addHeader(String headerName, String value) {
        headers.put(headerName, value);
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public void execute() {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            Objects.requireNonNull(url,"url未设置");
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(timeout)
                    .setConnectTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            HttpEntity entity = new StringEntity(params, charset);
            HttpUriRequest reqMethod = null;
            if (HttpMethod.GET.equals(method)) {
                reqMethod = RequestBuilder.get().setUri(url)
                        .setCharset(Charset.forName(charset))
                        .setConfig(config)
                        .setEntity(entity)
                        .build();
            } else if (HttpMethod.POST.equals(method)) {
                reqMethod = RequestBuilder.post().setUri(url)
                        .setCharset(Charset.forName(charset))
                        .setConfig(config)
                        .setEntity(entity)
                        .build();
            } else {
                throw new UnsupportedOperationException("暂不支持的请求类型");
            }

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                reqMethod.setHeader(entry.getKey(), entry.getValue());
            }
            httpclient = HttpClients.custom().build();
            response = httpclient.execute(reqMethod);
            StatusLine statusLine = response.getStatusLine();
            if (HttpStatus.SC_OK == (statusLine.getStatusCode())) {
                HttpEntity responseEntity = response.getEntity();
                result = EntityUtils.toByteArray(responseEntity);
                success = true;
            } else {
                errorMsg = "status:" + statusLine.getStatusCode() + ",reason:" + statusLine.getReasonPhrase();
            }
        } catch (Exception e) {
            throwable = e;
            errorMsg = e.getMessage();
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != httpclient) {
                    httpclient.close();
                }
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public Object getResult() {
        String temp = new String(result,Charset.forName(charset));
        return messageConvert.convert(temp,returnType);
    }

    public MessageConvert getMessageConvert() {
        return messageConvert;
    }

    public void setMessageConvert(MessageConvert messageConvert) {
        this.messageConvert = messageConvert;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    @Override
    public byte[] getBytes() {
        return result;
    }

    @Override
    public Throwable getError() {
        return throwable;
    }

    @Override
    public void fail() {
        this.success = false;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
