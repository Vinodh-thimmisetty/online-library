package com.vinodh.service;

import com.vinodh.dto.Homepage;
import org.springframework.stereotype.Service;

/**
 * @author thimmv
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Override
    public Homepage testHomePage() {
        return Homepage.builder().pageName("Welcome to Library !!").build();
    }

    @Override
    public Homepage testHomePage(final String userName) {
        return Homepage.builder().pageName("Welcome " + userName).build();
    }
}
