package ratefinder.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import ratefinder.bean.SearchEntry;

public interface ISearchEntryService {

    List<SearchEntry> fetchAll();


    List<SearchEntry> fetchAll(String exchange, String searchKeyword, int isJunk) throws ParseException;

    SearchEntry testFetch();

    Map<String, List<String>> getExchangeKeywords();

    Map<String, String> fetchExchangeUrls();

    int incrPagePoint(long id);


    List<SearchEntry> saveAll(List<SearchEntry> searchEntries);


    void removeOldEntries(String key, String keyword);


    List<SearchEntry> fetchAll(String exchange, String searchKeyword);


    boolean markAsJunk(List<Long> ids);
}
