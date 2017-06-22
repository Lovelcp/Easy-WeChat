package com.wooyoo.wechat.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtil {
    private RegexUtil() {
    }

    /**
     * 返回第一个被正则捕获到的字符串
     *
     * @param pattern
     * @param text
     * @return
     */
    public static String fetch(Pattern pattern, String text) {
        Matcher m = pattern.matcher(text);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    /**
     * 返回第一个被正则捕获到的字符串
     *
     * @param pattern
     * @param text
     * @param defaultValue
     * @return
     */
    public static String fetch(Pattern pattern, String text, String defaultValue) {
        String result = fetch(pattern, text);
        return result == null ? defaultValue : null;
    }

    /**
     * 返回第一个被正则捕获到的字符串
     *
     * @param pattern
     * @param text
     * @return
     */
    public static String fetch(String pattern, String text) {
        return fetch(Pattern.compile(pattern), text);
    }

    /**
     * 返回第一个被正则捕获到的字符串
     *
     * @param pattern
     * @param text
     * @param defaultValue
     * @return
     */
    public static String fetch(String pattern, String text, String defaultValue) {
        return fetch(Pattern.compile(pattern), text, defaultValue);
    }
}
