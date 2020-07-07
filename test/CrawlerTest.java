import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CrawlerTest {

    @Test
    public void checkUrl() {
        Crawler crawler=new Crawler("");
        Map<String, Map<String, Integer>> map=new HashMap<>();
        Map<String, Integer> m=new HashMap<>();
        m.put("word", 5);
        map.put("test", m);
        crawler.setMap(map);
        Assert.assertFalse(crawler.checkUrl("test1", "word"));
        Assert.assertFalse(crawler.checkUrl("test1", "word1"));
        Assert.assertTrue(crawler.checkUrl("test", "word"));
        Assert.assertFalse(crawler.checkUrl("test", "word1"));
    }

    @Test
    public void refreshMap() {
        Crawler crawler=new Crawler("");
        Map<String, Map<String, Integer>> map=new HashMap<>();
        Map<String, Integer> m=new HashMap<>();
        m.put("word", 5);
        map.put("test", m);
        crawler.setMap(map);
        crawler.refreshMap("test", "", "");
        Assert.assertEquals(map.size(), crawler.getMap().size());
        crawler.refreshMap("test1", "", "");
        Assert.assertNotEquals(map.size(), crawler.getMap().size());
    }
}