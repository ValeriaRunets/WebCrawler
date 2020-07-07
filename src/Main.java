import java.util.*;
import java.lang.*;

public class Main {
    private static String mainUrl = "https://www.acmilan.com";
    private Crawler crawler;
    private Map<String, Map<String, Integer>> map;
    private ArrayList<String> words;
    private CsvController csv = new CsvController();
    private ReadingWritingHelper helper = new ReadingWritingHelper();

    Main() {
        words = read();
        crawler = new Crawler(mainUrl);
        for (String word : words) {
            crawler.find("/en", word);
        }
        map = crawler.getMap();
        helper.writeAllResults(map, csv, words, "result.csv");
        helper.writeBestResult(map, csv, words, "bestResults.csv");
    }

    public ArrayList<String> read() {
        Scanner in = new Scanner(System.in);
        System.out.print("Input words using comma as delimiter: ");
        String words = in.nextLine();
        return helper.parse(words);
    }

    public static void main(String[] args) {
        new Main();
    }
}
