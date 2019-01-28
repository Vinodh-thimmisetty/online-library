package com.vinodh.utility;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class Utils {

    public String currentEndPoint() {
        ServletUriComponentsBuilder currentRequest = ServletUriComponentsBuilder.fromCurrentRequest();
        return currentRequest.buildAndExpand().getPath();
    }
}
