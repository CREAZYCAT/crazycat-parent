package top.crazycat.common.util;

import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/6/18
 * description:
 */
public class XmlParser {
    /**
     * xml转对象
     *
     * @param xml
     * @return
     * @throws JAXBException
     */
    @SuppressWarnings("unchecked")
    public static  <T> T convertToObj(String xml, Class<T> clazz) throws JAXBException {
        StringReader reader = new StringReader(xml);
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//        jaxbUnmarshaller.setProperty();
        return (T) jaxbUnmarshaller.unmarshal(reader);
    }

    /**
     * 将对象转为流程XML
     *
     * @param xmlObj
     * @return
     * @throws JAXBException
     */
    public static <T> String convertToXML(T xmlObj, String charset) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(xmlObj.getClass());
        StringWriter writer = new StringWriter();
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, charset);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT,false);
        marshaller.marshal(xmlObj, writer);
        String xmlStr = writer.toString();
        xmlStr = StringUtils.replace(xmlStr, "&quot;", "'");
        return xmlStr;
    }
}
