package com.jjangchen.crawler.client.model;

import org.springframework.util.MultiValueMap;

public interface RequestURI {
    String getPath();
    MultiValueMap<String, String> getParams();
}
