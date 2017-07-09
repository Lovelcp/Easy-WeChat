package com.wooyoo.wechat.util;

import java.util.Random;

public final class RandomUtil {
    private RandomUtil() {
    }

    public static String generateRandomNumbers(int len) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
