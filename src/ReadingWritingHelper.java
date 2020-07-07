import com.opencsv.CSVWriter;

import java.util.*;
import java.util.stream.Collectors;

public class ReadingWritingHelper {
    public ArrayList<String> parse(String s) {
        ArrayList<String> arr = new ArrayList<>();
        if (s.equals("")) {
            return null;
        }
        for (String str : s.split(", ")) {
            arr.add(str);
        }
        return arr;
    }

    public void writeAllResults(Map<String, Map<String, Integer>> map, CsvController csv, ArrayList<String> words, String filename) {
        ArrayList<String[]> lines = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            String[] line = new String[words.size() + 2];
            line[0] = entry.getKey();
            int i = 1;
            int res = 0;
            for (String word : words) {
                line[i++] = entry.getValue().getOrDefault(word, 0).toString();
                res += entry.getValue().getOrDefault(word, 0);
            }
            line[line.length - 1] = Integer.toString(res);
            lines.add(line);
        }
        csv.write(lines, filename);
    }

    public void writeBestResult(Map<String, Map<String, Integer>> map, CsvController csv, ArrayList<String> words, String filename) {
        Map<String, Integer> helpMap = makeHelpMap(map);
        int val = 0;
        ArrayList<String[]> lines = new ArrayList<>();
        for (Map.Entry<String, Integer> it : helpMap.entrySet()) {
            if (val++ == 10) {
                break;
            }
            System.out.print(it.getKey()+" ");
            String[] line = new String[words.size() + 2];
            line[0] = it.getKey();
            int i = 1;
            int res = 0;
            for (String word : words) {
                System.out.print(map.get(it.getKey()).getOrDefault(word, 0).toString()+" ");
                line[i++] = map.get(it.getKey()).getOrDefault(word, 0).toString();
                res += map.get(it.getKey()).getOrDefault(word, 0);
            }
            System.out.println(res);
            line[line.length - 1] = Integer.toString(res);
            lines.add(line);
        }
        csv.write(lines, filename);
    }

    public Map<String, Integer> makeHelpMap(Map<String, Map<String, Integer>> map) {
        Map<String, Integer> helpMap = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            int sum = 0;
            for (Map.Entry<String, Integer> it : entry.getValue().entrySet()) {
                sum += it.getValue();
            }
            helpMap.put(entry.getKey(), sum);
        }
        helpMap = helpMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return helpMap;
    }
}
