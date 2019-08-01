package top.crazycat.common.util.http;

import org.apache.commons.lang.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/18
 * description:
 */
public class HttpClientUtil {

    /**
     * form参数可用
     *
     * @param param
     */
    public static String getUrlParamsByMap(Map<String, Object> param) {
        if (param == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            sb.append(entry.getKey()).append("=")
                    .append(entry.getValue()).append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        System.out.println(s);
        return s;
    }

    /**
     * 将url参数转换成map
     *
     * @param
     * @return
     */
    public static Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        HttpClient client = new HttpClient(Code.class);
        client.setUrl("http://localhost:9090/form");
        client.setMethod(HttpMethod.POST);
        client.setParams("a={\"code\":\"娃哈哈\"}");
//        client.setParams("a=<root><code>娃哈哈</code></root>");
        client.setMessageConvert(MessageConvert.JSON_CONVERT);
        client.execute();
        if(client.isSuccess()) {
            for (byte b : client.getBytes()) {
                System.out.print(b);
            }
            System.out.println();
            System.out.println(client.getResult());
        }else
            System.out.println(client.getErrorMsg());
    }

    @XmlRootElement(name = "root")
    static class Code {
        private String code;

        @XmlElement(name = "code")
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "Code{" +
                    "code='" + code + '\'' +
                    '}';
        }
    }
}
