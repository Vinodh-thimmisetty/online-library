package com.vinodh.service;

import com.vinodh.dto.Homepage;

/**
 * @author thimmv
 */
public interface HomeService {
    Homepage testHomePage();
    Homepage testHomePage(String userName);
}
