package top.crazycat.common.util.http;

import com.alibaba.fastjson.JSON;
import top.crazycat.common.util.XmlParser;

import javax.xml.bind.JAXBException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/8/1
 * description:
 */
@FunctionalInterface
public interface MessageConvert {

    MessageConvert JSON_CONVERT = JSON::parseObject;

    MessageConvert XML_CONVERT = new MessageConvert() {

        @Override
        public <T> T convert(String source, Class<T> clazz) {
            try {
                return XmlParser.convertToObj(source,clazz);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
    };

    <T> T convert(String source,Class<T> clazz);
}
