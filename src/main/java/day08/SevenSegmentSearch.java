package day08;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class SevenSegmentSearch {

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        List<String> csvLines = FileUtils.getCsvLines("day8.csv");
        List<Integer> uniqueNumbers = new ArrayList();

        uniqueNumbers.add(2); // 1
        uniqueNumbers.add(3); // 7
        uniqueNumbers.add(4); // 4
        uniqueNumbers.add(7); // 8

        int counter = 0;
        for (String line : csvLines) {
            String[] split = line.split(" \\| ");
            String[] output = split[1].split(" ");
            for (String number : output) {
                if (uniqueNumbers.contains(number.length())) {
                    counter++;
                }
            }

        }
        System.out.println(counter);
    }
}
