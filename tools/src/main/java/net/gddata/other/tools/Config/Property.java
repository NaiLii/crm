package net.gddata.other.tools.Config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;

/**
 * Created by knix on 16/3/10.
 */
public class Property {
    public static Configuration getConfig(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("Property file ID must be specified.");
        }
        String filename = name + ".properties";
        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            if (stream != null) {
                config.load(stream);
            }
        } catch (Exception e) {
            //log.warn("Can not load configuration file", e);
        }

        return config;
    }
}
