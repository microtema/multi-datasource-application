package it.com.e2open.datahub.servlet;

import com.e2open.datahub.DXApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DXApplication.class)
public class LoginFormServletIT {
    @Inject
    private TestRestTemplate restTemplate;

    @Test
    public void doGet () throws Exception {
        ResponseEntity<String> exchange = restTemplate.exchange ("/form", HttpMethod.GET, null, String.class);
        Assert.assertNotNull (exchange);
        Assert.assertEquals (HttpStatus.OK, exchange.getStatusCode ());
        Assert.assertEquals ("1", exchange.getBody ());
    }

}