package com.cdtft.springframework.util;

/**
 * @author: wangcheng
 * @date: 2021年10月14 17:31
 */
public class IStringUtils {

    public static String lowerFirst(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        if (charSequence.length() > 0) {
            char firstChar = charSequence.charAt(0);
            if (Character.isUpperCase(firstChar)) {
                return Character.toLowerCase(firstChar) + charSequence.subSequence(1, charSequence.length()).toString();
            }
        }
        return charSequence.toString();
    }
}
