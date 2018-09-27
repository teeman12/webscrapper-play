package ratefinder;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ratefinder.bean.SearchEntry;
import ratefinder.service.ISearchEntryService;

@SpringBootApplication(scanBasePackages = "ratefinder")
@RestController
public class Application {


    @Autowired
    private ISearchEntryService searchEntryService;


    @CrossOrigin
    @GetMapping("/")
    public String home() {
        return "rate finder";
    }

    @CrossOrigin
    @GetMapping("/getTestSearchEntry")
    public SearchEntry testEntry() {
        return searchEntryService.testFetch();
    }

    @CrossOrigin
    @GetMapping("/getAllSearchEntries")
    public List<SearchEntry> searchAllEntries() {
        return searchEntryService.fetchAll();
    }

    @CrossOrigin
    @GetMapping("/getSearchEntries")
    public List<SearchEntry> searchEntries(String exchange, String keyword, int isJunk) throws ParseException {
        return searchEntryService.fetchAll(exchange, keyword, isJunk);
    }

    @CrossOrigin
    @GetMapping("/getExchangeKeywords")
    public Map<String, List<String>> getExchangeKeywords() {
        return searchEntryService.getExchangeKeywords();
    }

    @CrossOrigin
    @GetMapping("/getExchangeUrls")
    public Map<String, String> fetchExchangeUrls(){
        return searchEntryService.fetchExchangeUrls();
    }

    @CrossOrigin
    @GetMapping("/incPagePoint")
    public int incrPagePoint(long searchEntryId){
        return searchEntryService.incrPagePoint(searchEntryId);
    }

    @CrossOrigin
    @GetMapping("/markAsJunk")
    public boolean markAsJunk(@RequestParam Long[] ids){
         return searchEntryService.markAsJunk(Arrays.asList(ids));
    }


    public static void main(String args[]){
        SpringApplication.run(Application.class,args );


    }

}
