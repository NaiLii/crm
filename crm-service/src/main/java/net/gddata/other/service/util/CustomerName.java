package net.gddata.other.service.util;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by knix on 16/2/25.
 */
@Slf4j
public class CustomerName {
    public static String getPingYin(String src) {

        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        for (int i = 0; i < t0; i++) {
            // 判断是否为汉字字符
            if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    //badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
                t4 += t2[0];
            } else
                t4 += java.lang.Character.toString(t1[i]);
        }
        return t4;
    }
}
