package com.vinodh.com.vinodh.oauth2;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

/**
 * @author thimmv
 */
@Ignore
public class OaAuth2Test {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setup() {
        testRestTemplate.withBasicAuth("thimmv", "Vin@5052#Vin");
    }

    @Test
    public void test() {
        Assertions.assertThat("My Name is Vinodh")
                .isEqualTo("My Name is Vinodh");
    }

    @Test
    public void getOAuth2Code() {
        ResponseEntity<String> response = testRestTemplate
                .withBasicAuth("thimmv", "Vin@5052#Vin")
                .getForEntity("https://fedlogin.cat.com/as/authorization.oauth2?" +
                                "client_id=sitesolution_client&" +
                                "response_type=code&" +
                                "pfidpadapterid=OAuthAdapterCCDS&" +
                                "scope=openid%20profile&" +
                                "state=ex78pky4&" +
                                "redirect-uri=x-caterpillar-sitesolution://oauth2/authorize",
                        String.class);

        System.out.println(response.getBody());
    }

    @After
    public void cleanup() {

    }

}
