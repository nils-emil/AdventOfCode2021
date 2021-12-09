package day;

import utils.FileUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Example {

    public static final int DAY_NR = 9;

    public static void main(String[] args) {
//        solve("day" + DAY_NR + ".csv");
        solve("day" + DAY_NR + "_ex.csv");
    }

    private static void solve(String file) {
        List<String> csvLines = FileUtils.getCsvLines(file);

//        String[][] map = csvLines.stream()
//                .map(arr -> arr.split(""))
//                .toArray(String[][]::new);

//        List<Integer> numbers = Arrays.stream(csvLines.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

    }

}
