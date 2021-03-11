package com.realexpayments.hpp;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;

import java.util.HashMap;
import java.util.Map;

import static com.realexpayments.hpp.Constants.SLASH;

public class Utils {

    public static String getHostPath(String urlString) {
        return urlString.substring(0, urlString.lastIndexOf(getRelativePath(urlString)) - 1);
    }

    public static String getRelativePath(String urlString) {
        Uri uri = Uri.parse(urlString);
        String path = uri.getPath();
        return (path.startsWith(SLASH)) ? path.substring(1) : path;
    }

    public static String getRelativePathEncoded(String urlString) {
        Uri uri = Uri.parse(urlString);
        String encodedPath = uri.getEncodedPath();
        return (encodedPath.startsWith(SLASH)) ? encodedPath.substring(1) : encodedPath;
    }

    public static String decode(String encodedValue) {
        byte[] decodedValue = Base64.decode(encodedValue, Base64.DEFAULT);
        return new String(decodedValue);
    }

    public static Map<String, String> collectValidMapValues(Map<String, String> inputMap) {
        HashMap<String, String> outputMap = new HashMap<>();

        if (inputMap != null && !inputMap.isEmpty()) {
            for (String key : inputMap.keySet()) {
                String value = inputMap.get(key);

                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    outputMap.put(key, value);
                }
            }
        }

        return outputMap;
    }

}
