package ratefinder.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScraperRunServiceTest {


    @Autowired
    private ScraperRunService scraperRunService;


    @Test
    public void testRun() {

        //scraperRunService.run();

        System.out.println("Scrap run" );
    }



}
