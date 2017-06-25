package com.wooyoo.wechat.util;

import java.net.URI;
import java.net.URISyntaxException;

public final class HttpUtil {
    private HttpUtil() {
    }

    public static String getSchemeWithHost(String url) {
        try {
            URI uri = new URI(url);
            return uri.getScheme() + "://" + uri.getHost();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
