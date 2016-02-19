package net.gddata.other.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ReadConf {

    //    配置文件
    private static Properties props = new Properties();

    static {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dev.properties"));
        } catch (FileNotFoundException var1) {
            var1.printStackTrace();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }

    public static void setValue(String key, String value) {
        props.setProperty(key, value);
    }

}
