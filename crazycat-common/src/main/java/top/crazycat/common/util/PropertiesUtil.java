package top.crazycat.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/7/2
 * description:
 */
public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";

    public PropertiesUtil() {
    }

    public static Properties getConfig(String configFile) {
        return getConfig(configFile, DEFAULT_CHARSET_NAME);
    }

    public static Properties getConfig(String configFile, String charsetName) {
        if (charsetName == null) {
            throw new IllegalArgumentException("Argu charsetName cannot be null");
        } else if (configFile == null) {
            throw new IllegalArgumentException("Argu configFile cannot be null");
        } else {
            Properties prop = new Properties();
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(configFile);
            if (null == in) {
                return null;
            } else {
                try {
                    prop.load(in);
                } catch (IOException var5) {
                    logger.error(var5.getMessage(), var5);
                }

                return prop;
            }
        }
    }
}
