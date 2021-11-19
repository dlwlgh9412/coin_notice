package com.jjangchen.crawler.util;

public class QuoteUtil {
    public static Long doubleToLong(Double price) {
        if(price.toString().contains("E")) {
            int index = price.toString().indexOf("E");
            StringBuilder stringBuilder = new StringBuilder(price.toString().substring(0, index - 1).replace(".", ""));
            for(int i = stringBuilder.indexOf("."); i <= index + 1; i++)
                if(stringBuilder.length() <= i) {
                    stringBuilder.append("0");
                }
            return Long.valueOf(stringBuilder.toString());
        }
        return price.longValue();
    }
}
