package ratefinder.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import ratefinder.bean.SearchEntry;

@Service
public class ScraperRunService {

    @Autowired
    SearchEntryService searchEntryService;

    @Autowired
    ApplicationContext applicationContext;

    static Map<String, String> scraperBeanMap;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("CME", "cmeScraper");
        map.put("EURO", "eurexScraper");
        scraperBeanMap = Collections.unmodifiableMap(map);
    }


    public void run() {
        List<SearchEntry> results = new ArrayList<>();

        Map<String, List<String>> exchangeMap = searchEntryService.getExchangeKeywords();
        for(Map.Entry<String, List<String>> entry  : exchangeMap.entrySet()) {
            if(scraperBeanMap.containsKey(entry.getKey())) {
                List<String> keywords = exchangeMap.get(entry.getKey());
                for(String keyword : keywords) {
                    searchEntryService.removeOldEntries(entry.getKey(),keyword);
                    RateScraper scraper = applicationContext.getBean(scraperBeanMap.get(entry.getKey()), RateScraper.class);
                    results.addAll(scraper.scrape(keyword));
                }
            }
        }
        searchEntryService.saveAll(results);
    }

}
