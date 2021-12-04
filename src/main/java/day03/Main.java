package day03;

import utils.FileUtils;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        List<String> csvLines = FileUtils.getCsvLines("day3.csv");
        int numberOfBinaries = csvLines.get(0).split("").length;
        HashMap<Integer, Integer> counter= new HashMap<>();
        for (int i = 0; i < numberOfBinaries; i++) {
            counter.put(i, 0);
        }
        for (String line : csvLines) {
            String[] split = line.split("");
            for (int i = 0; i<numberOfBinaries; i++) {
                Integer currentCount = counter.get(i);
                if (split[i].equals("0")) {
                    counter.put(i, currentCount - 1);
                } else {
                    counter.put(i, currentCount + 1);
                }
            }
        }
        // manual labour
        // 000100011001 = 281 * 3814
        // 111011100110 = 3814

        System.out.println(counter);

    }

}
