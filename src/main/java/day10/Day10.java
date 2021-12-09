package day10;

import utils.FileUtils;

import java.util.List;

public class Day10 {


    public static final int DAY_NR = 10;

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
