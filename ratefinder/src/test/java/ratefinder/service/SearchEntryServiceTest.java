package ratefinder.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ratefinder.bean.SearchEntry;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchEntryServiceTest {


    @Autowired
    private ISearchEntryService searchEntryService;


    @Test
    public void testFindAll() throws Exception {
        List<SearchEntry> cityList = searchEntryService.fetchAll();
        System.out.println(" search entry count " + cityList.size());
    }

    @Test
    public void testFindAllFilter() throws Exception {
        List<SearchEntry> cityList = searchEntryService.fetchAll("testExchange4", "testkeyword4", 1);
        System.out.println(" search entry count " + cityList.size());
    }

    @Test
    public void testIncPageCount() throws Exception {
        int pc = searchEntryService.incrPagePoint(4);
        System.out.println(" new page count " + pc);
    }


    //@Test
    public void testSaveAll() throws Exception {

        SearchEntry searchEntry1 =  new SearchEntry("test1", "http://www.testMy.com", "testExchange1", "testkeyword3", 11, 0,
                new Date());
        SearchEntry searchEntry2 =  new SearchEntry("test5", "http://www.testMy.com", "testExchange5", "testkeyword4", 10, 1,
                new Date());
        List<SearchEntry> searchEntries = searchEntryService.saveAll(Arrays.asList(searchEntry1,searchEntry2));

        System.out.println(" search entry count " + searchEntries.size());
    }


}
