package net.gddata.other.crm.Util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;

/**
 * Created by knix on 16/2/29.
 */
@Slf4j
public class PropertyUtil {
    public static Configuration getConfig(String pid) {
        if (StringUtils.isEmpty(pid)) {
            throw new RuntimeException("Property file ID must be specified.");
        }
        String filename = pid + ".properties";
        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            if (stream != null) {
                config.load(stream);
            }
        } catch (Exception e) {
            log.warn("Can not load configuration file", e);
        }

        return config;
    }
}
