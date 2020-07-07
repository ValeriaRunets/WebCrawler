import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

import java.io.IOException;

public class Crawler {
    private static final int URLDEPTH = 3;
    private Map<String, Map<String, Integer>> map = new HashMap<>();
    private String mainUrl;

    public Crawler(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public void find(String url, String word) {
        try {
            Connection connection = Jsoup.connect(mainUrl + url);
            Document doc = connection.get();
            String str = doc.body().text();
            refreshMap(url, str, word);
            Elements frames = doc.body().getElementsByTag("a");
            for (Element frame : frames) {
                String text = frame.attr("href");
                if (text.startsWith(url) && text.split("/").length - 1 <= URLDEPTH && !checkUrl(text, word)) {
                    find(text, word);
                }
            }
        } catch (IOException ex) {
        }
    }

    public boolean checkUrl(String url, String word) {
        if (map.containsKey(mainUrl + url)) {
            return map.get(mainUrl + url).containsKey(word);
        } else return false;
    }

    public void refreshMap(String url, String text, String word) {
        if (map.containsKey(mainUrl + url)) {
            map.get(mainUrl + url).put(word, text.split(word).length - 1);
        } else {
            map.put(mainUrl + url, new HashMap<>());
            map.get(mainUrl + url).put(word, text.split(word).length - 1);
        }
    }

    public Map<String, Map<String, Integer>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<String, Integer>> map) {
        this.map = new HashMap<>(map);
    }
}
