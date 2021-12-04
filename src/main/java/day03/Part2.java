package day03;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) {
        solve();
    }

    private static int solve() {
        List<String> csvLines = FileUtils.getCsvLines("day3.csv");
        int oxigenIndex = 0;
        List<String> oxigenList = new ArrayList<>(csvLines);
        while (oxigenList.size() > 1) {
            int onesCount = findNumberOfOccurencesAtIndex(oxigenList, "1", oxigenIndex);
            int zerosCount = oxigenList.size() - onesCount;
            if (zerosCount > onesCount) {
                oxigenList = removeNumbersStartingWith(oxigenList, "0", oxigenIndex);
            } else {
                oxigenList = removeNumbersStartingWith(oxigenList, "1", oxigenIndex);
            }
            oxigenIndex++;
        }

        int scubberIndex = 0;
        List<String> scubberList = new ArrayList<>(csvLines);
        while (scubberList.size() > 1) {
            int onesCount = findNumberOfOccurencesAtIndex(scubberList, "1", scubberIndex);
            int zerosCount = scubberList.size() - onesCount;
            if (zerosCount > onesCount) {
                scubberList = removeNumbersStartingWith(scubberList, "1", scubberIndex);
            } else {
                scubberList = removeNumbersStartingWith(scubberList, "0", scubberIndex);
            }
            scubberIndex++;
        }

        System.out.println(oxigenIndex);
        return Integer.parseInt(oxigenList.get(0), 2) * Integer.parseInt(scubberList.get(0), 2);
    }

    private static int findNumberOfOccurencesAtIndex(List<String> csvLines, String startsWith, int index) {
        int counter = 0;
        for (String line : csvLines) {
            if (line.charAt(index) == startsWith.charAt(0)) {
                counter++;
            }
        }
        return counter;
    }

    private static List<String> removeNumbersStartingWith(List<String> csvLines, String startsWith, int index) {
        return csvLines.stream()
                .filter(e -> e.charAt(index) == startsWith.charAt(0))
                .collect(Collectors.toList());
    }


}
