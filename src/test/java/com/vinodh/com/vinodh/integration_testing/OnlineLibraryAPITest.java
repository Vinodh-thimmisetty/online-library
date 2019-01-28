package com.vinodh.com.vinodh.integration_testing;

import com.vinodh.dto.Homepage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.client.Hop;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author thimmv
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OnlineLibraryAPITest {
    private static final String GET_HOMEPAGE = "/library/home";
    private static final String GET_HOMEPAGE_CUSTOM = "/library/home/Thimmisetty";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setup() {
    }

    @Test
    public void homePageAPI_test() throws URISyntaxException {
//        ResponseEntity<Homepage> homePageAPIResponse = this.testRestTemplate.getForEntity(GET_HOMEPAGE, Homepage.class);
//        assertEquals(HttpStatus.OK, homePageAPIResponse.getStatusCode());
//        assertEquals("Welcome to Library !!", homePageAPIResponse.getBody().getPageName());

        ParameterizedTypeReference<Resource<Homepage>> parameterizedTypeReference = new ParameterizedTypeReference<Resource<Homepage>>() {
        };

        Traverson traverson = new Traverson(new URI("http://localhost:1111" + GET_HOMEPAGE_CUSTOM), MediaType.ALL);
//        ResponseEntity<Resource<Homepage>> customHomePageAPIResponse = this.testRestTemplate.exchange(GET_HOMEPAGE_CUSTOM, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Homepage>>() {
//        });

        Link link = traverson.follow(Hop.rel("library-home")).asLink();
        System.out.println(link);
        ResponseEntity<Resource<Homepage>> customHomePageAPIResponse = this.testRestTemplate
                .exchange(link.getHref(), HttpMethod.GET, null, parameterizedTypeReference);
//        Resource<Homepage> homepageResource = traverson.follow().toObject(parameterizedTypeReference);
        System.out.println(customHomePageAPIResponse);
        System.out.println("-------------------");
//        assertEquals(HttpStatus.OK, customHomePageAPIResponse.getStatusCode());
//        assertEquals("Welcome Thimmisetty", customHomePageAPIResponse.getBody().getContent().getPageName());

    }

    @After
    public void cleanup() {
    }


}

