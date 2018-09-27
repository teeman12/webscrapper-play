package ratefinder.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ratefinder.bean.SearchEntry;

@Service("cmeScraper")
public class CMEScraper implements RateScraper {

    @Override
    public List<SearchEntry> scrape(String keyword) {
        List<SearchEntry> results = new ArrayList<>();
        String baseUrl = "https://www.theice.com";
        String searchQuery = keyword ;

        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
          String searchUrl = "https://www.theice.com/search?site=theICE%7CIntercontinentalExchange&client=ice_frontend_html&proxystylesheet=ice_frontend_html&output=xml_no_dtd&filter=0&getfields=*&num=10&q="
        + URLEncoder.encode(searchQuery, "UTF-8");
          HtmlPage page = client.getPage(searchUrl);
          List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//ul[@class='search-results']") ;

              List<HtmlListItem> elements = (List<HtmlListItem>) items.get(0).getByXPath("//li[@class=' item-pdf']");
              for(HtmlListItem element : elements) {
                  SearchEntry program = new SearchEntry();
                  program.setExchange("CME");
                  program.setKeyWord(keyword);
                  program.setLink(element.getFirstChild().getFirstChild().getAttributes().getNamedItem("href").getNodeValue());
                  program.setName(element.getFirstChild().getFirstChild().asText());
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
