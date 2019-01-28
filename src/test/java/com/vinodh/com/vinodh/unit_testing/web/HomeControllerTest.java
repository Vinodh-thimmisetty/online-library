package com.vinodh.com.vinodh.unit_testing.web;

import com.vinodh.dto.Homepage;
import com.vinodh.service.HomeService;
import com.vinodh.utility.Utils;
import com.vinodh.web.HomeController;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author thimmv
 */
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomeService homeService;
    @MockBean
    private Utils utils;

    @Before
    public void setup() {
        when(this.homeService.testHomePage()).thenReturn(new Homepage("Vinodh"));
        when(this.homeService.testHomePage(anyString())).thenReturn(new Homepage("Welcome Thimmisetty"));
    }

    @Test
    public void homepage_test() throws Exception {
        this.mockMvc
                .perform(get("/library/home"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageName", Matchers.is("Vinodh")));
    }

    @Test
    public void homepage_withPathParam_test() throws Exception {
        this.mockMvc
                .perform(get("/library/home/Thimmisetty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageName", Matchers.is("Welcome Thimmisetty")))
                .andExpect(jsonPath("$._links.library-home.href", Matchers.containsString("library/home")));
    }

    @After
    public void cleanup() {
    }

}
