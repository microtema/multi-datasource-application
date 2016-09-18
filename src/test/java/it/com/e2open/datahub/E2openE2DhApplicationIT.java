package it.com.e2open.datahub;

import com.e2open.datahub.E2openE2DhApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = E2openE2DhApplication.class)
public class E2openE2DhApplicationIT {

    @Test
    public void contextLoads() {
    }

}
