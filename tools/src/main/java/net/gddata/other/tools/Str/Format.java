package net.gddata.other.tools.Str;

import io.jsonwebtoken.impl.TextCodec;

/**
 * Created by knix on 16/3/10.
 */
public class Format {
    public static String ToBase64(String str) {
        return TextCodec.BASE64.encode(str);
    }
}
