import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ReadingWritingHelperTest {

    @Test
    public void parse() {
        ReadingWritingHelper helper = new ReadingWritingHelper();
        ArrayList<String> arr = helper.parse("word1, word2");
        Assert.assertEquals(2, arr.size());
        arr = helper.parse("word1,word2");
        Assert.assertEquals(1, arr.size());
        arr = helper.parse("");
        Assert.assertNull(arr);
        arr = helper.parse("word");
        Assert.assertEquals(1, arr.size());
    }

    @Test
    public void writeAllResults() {
        ReadingWritingHelper helper = new ReadingWritingHelper();
        Map<String, Integer> m = new HashMap<>();
        m.put("word", 5);
        Map<String, Map<String, Integer>> map = new HashMap<>();
        map.put("url1", m);
        m.put("word", 6);
        map.put("url2", m);
        CsvController csv=new CsvController();
        ArrayList<String> words=new ArrayList<>();
        words.add("word");
        helper.writeAllResults(map, csv, words, "result.csv");
    }

    @Test
    public void writeBestResult() {
    }

    @Test
    public void makeHelpMap() {
        ReadingWritingHelper helper = new ReadingWritingHelper();
        Map<String, Integer> m = new HashMap<>();
        m.put("word", 5);
        Map<String, Map<String, Integer>> map = new HashMap<>();
        map.put("url1", m);
        m.put("word", 6);
        map.put("url2", m);
        Assert.assertEquals(map.size(), helper.makeHelpMap(map).size());
    }
}