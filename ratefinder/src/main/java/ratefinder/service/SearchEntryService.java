package ratefinder.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ratefinder.bean.SearchEntry;
import ratefinder.repository.SearchEntryRepository;

@Service
public class SearchEntryService  implements ISearchEntryService{

    @Autowired
    private SearchEntryRepository searchEntryRepository;


    @Override
    public List<SearchEntry> fetchAll() {

        List<SearchEntry> searchEntries = (List<SearchEntry>)searchEntryRepository.findAll();

        return searchEntries;
    }

    @Override
    public List<SearchEntry> fetchAll(String exchange, String searchKeyword, int isJunk) throws ParseException {
        List<SearchEntry> searchEntries = fetchAll();
        Date date = new Date();
        date = new DateTime(date).minusDays(2).toDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        for(SearchEntry entry : searchEntries) {
            if(entry.getDiscoveryDate().compareTo(dateFormat.parse(dateFormat.format(date))) >= 0) {
                entry.setIsNewRecord(1);
            }
        }
        return searchEntries.stream()
                .filter(se -> se.getExchange().equals(exchange) && se.getKeyWord().equals(searchKeyword) && (isJunk == 1 || se.getIsJunk()==isJunk))
                .sorted((Comparator.comparing(SearchEntry::getPagePoint).reversed()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchEntry> fetchAll(String exchange, String searchKeyword) {
        List<SearchEntry> searchEntries = fetchAll();

        return searchEntries.stream()
                .filter(se -> se.getExchange().equals(exchange) && se.getKeyWord().equals(searchKeyword))
                .sorted((Comparator.comparing(SearchEntry::getPagePoint).reversed()))
                .collect(Collectors.toList());
    }

    @Override
    public SearchEntry testFetch() {
        SearchEntry searchEntry =  new SearchEntry("test", "http://www.testMy.com", "testExchange", "testkeyword", 12, 0,
                new Date());

        return  searchEntry;
    }

    @Override
    public List<SearchEntry> saveAll(List<SearchEntry> searchEntries) {
        return (List<SearchEntry>)searchEntryRepository.save(searchEntries);
    }

    @Override
    public int incrPagePoint(long id) {

        SearchEntry searchEntry = searchEntryRepository.findOne(id);
        searchEntry.setPagePoint(searchEntry.getPagePoint() + 1);
        SearchEntry searchEntry1 = searchEntryRepository.save(searchEntry);
        return searchEntry1.getPagePoint();
    }

    @Override
    public Map<String, List<String>> getExchangeKeywords() {

        Map<String, List<String>> exchangeKeywords  = new HashMap<>();

        exchangeKeywords.put("CME", Arrays.asList("FEE REDUCTION", "Volume discounts", "Fees schedule", "Trading Incentive"));

        exchangeKeywords.put("ICE", Arrays.asList("FEE REDUCTION", "Incentive"));

        exchangeKeywords.put("EURO", Arrays.asList("eurex pricing", "Trading Incentive", "New incentive"));

        return exchangeKeywords;
    }

    @Override
    public Map<String, String> fetchExchangeUrls() {
        Map<String, String> exchangeKeywords = new HashMap<>();
        exchangeKeywords.put("CME", "https://www.cmegroup.com");
        exchangeKeywords.put("ICE", "https://www.theice.com");
        exchangeKeywords.put("EURO", "http://www.eurexchange.com");
        return exchangeKeywords;
    }

    @Override
    public void removeOldEntries(String exchange, String keyWord) {
        List<SearchEntry> entries = fetchAll(exchange,keyWord);
        searchEntryRepository.delete(entries);

    }

    @Override
    public boolean markAsJunk(List<Long> ids) {
        List<SearchEntry> entries = fetchAll();
        List<SearchEntry> updatedEntries = new ArrayList<>();
        for(SearchEntry e : entries) {
            if(ids.contains(e.getId())) {
                updatedEntries.add(e);
            }
        }
        updatedEntries.stream().forEach(e -> e.setIsJunk(1));
        try {
            searchEntryRepository.save(updatedEntries);
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }
}
