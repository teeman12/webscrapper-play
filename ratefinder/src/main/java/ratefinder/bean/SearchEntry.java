package ratefinder.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name= "search_entry", schema = "sandbox")
public class SearchEntry implements Serializable {

    private static final long serialVersionId = 1L;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String name;

    private String link;

    private String exchange;

    private String keyWord;

    private int pagePoint;

    private int isJunk;

    private Date discoveryDate;

    @Transient
    private int isNewRecord;

    public SearchEntry() {
    }

    public SearchEntry(String name, String link, String exchange, String keyWord, int pagePoint,
            int isJunk, Date discoveryDate) {
        this.name = name;
        this.link = link;
        this.exchange = exchange;
        this.keyWord = keyWord;
        this.pagePoint = pagePoint;
        this.isJunk = isJunk;
        this.discoveryDate = discoveryDate;
    }

     public int getIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(int isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getIsJunk() {
        return isJunk;
    }

    public void setIsJunk(int isJunk) {
        this.isJunk = isJunk;
    }

    public Date getDiscoveryDate() {
        return discoveryDate;
    }

    public void setDiscoveryDate(Date discoveryDate) {
        this.discoveryDate = discoveryDate;
    }

    public int getPagePoint() {
        return pagePoint;
    }

    public void setPagePoint(int pagePoint) {
        this.pagePoint = pagePoint;
    }
}
