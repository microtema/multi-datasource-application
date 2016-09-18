package it.com.e2open.datahub;

import com.e2open.datahub.DXApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DXApplication.class)
public class DXApplicationIT {

    @Test
    public void contextLoads() {
    }

}
