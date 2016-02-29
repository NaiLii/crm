package net.gddata.other.crm.Util;

import io.jsonwebtoken.impl.TextCodec;
import org.apache.commons.configuration.Configuration;

/**
 * Created by knix on 16/2/29.
 */
public class SecretKey {
    public static String base64KeyStr = "";

    public static String getBase64Key() {
        if (base64KeyStr.equals("")) {
            try {
                Configuration config = PropertyUtil.getConfig("auth");
                String secretKey = config.getString("secret.key");
                base64KeyStr = TextCodec.BASE64.encode(secretKey);
            } catch (Exception e) {
                return "";
            }

        }
        return base64KeyStr;
    }
}
