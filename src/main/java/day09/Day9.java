package day09;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {
    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        List<String> csvLines = FileUtils.getCsvLines("day9.csv");
        List<List<Integer>> map = new ArrayList<>();
        for (String line : csvLines) {
            List<Integer> split = Arrays.asList(line.split("")).stream().map(e -> Integer.parseInt(e)).collect(Collectors.toList());
            map.add(split);
        }
        int counter = 0;
        for (int x = 0; x < map.get(0).size(); x++) {
            for (int y = 0; y < map.size(); y++) {
                int value = map.get(y).get(x);
                boolean al = isLowSpot(map, x, y, value);
                if (al) {
                    counter += 1 + value;
                }
            }
        }
        System.out.println(counter);

    }

    private static boolean isLowSpot(List<List<Integer>> map, int x, int y, int value) {
        if (x == 0 && y == 9) {
            System.out.println("");
        }
        List<Integer> neigbours = new ArrayList<>();
        for (int x1 = x - 1; x1 <= x + 1; x1++) {
            for (int y1 = y - 1; y1 <= y + 1; y1++) {
                try {
                    if (y1 == y && x1 == x) {
                    } else {
                        Integer tempvalue = map.get(y1).get(x1);
                        neigbours.add(tempvalue);
                    }
                } catch (Exception e) {
                }
            }
        }

        for (Integer el : neigbours) {
            if (el <= value) {
                return false;
            }
        }
        System.out.println( x +":"  + y + " smallest: true");
        return true;
    }

}
