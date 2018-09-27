/*
 * Copyright (c) 2018 Arcesium LLC. All rights reserved.
 *
 * This software is the confidential and proprietary information of Arcesium LLC. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license
 * agreement you entered into with Arcesium LLC.
 */
package ratefinder.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ratefinder.bean.SearchEntry;

/**
 * @author somanip
 */
@Service
public class EurexScraper implements RateScraper {

    @Override
    public List<SearchEntry> scrape(String keyword) {
        List<SearchEntry> results = new ArrayList<>();
        String baseUrl = "http://www.eurexchange.com";
        String searchQuery = keyword ;

        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
          String searchUrl = "http://www.eurexchange.com/exchange-en/search/3100!search?wt_form=1&query=" + URLEncoder.encode(searchQuery, "UTF-8");
          HtmlPage page = client.getPage(searchUrl);
          List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//div[@class='searchList list']") ;
              Iterable<DomElement> elements = items.get(0).getElementsByTagName("ul").get(1).getChildElements();
              while(elements.iterator().hasNext()) {
                  DomElement e = elements.iterator().next();
                  SearchEntry program = new SearchEntry();
                  program.setExchange("EURO");
                  program.setKeyWord(keyword);
                  program.setLink(baseUrl + e.getLastChild().getLastChild().getLastChild().getAttributes().getNamedItem("href").getNodeValue());
                  program.setName(e.getLastChild().getFirstChild().asText());
                  results.add(program);
              }

        }catch(Exception e){
          e.printStackTrace();
        }
        for(SearchEntry p : results) {
            System.out.println(p.getName() +  "_" + p.getLink());
        }

        return results;
    }

}
