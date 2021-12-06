package day06;

import utils.FileUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LanternfishImproved {

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        List<String> csvLines = FileUtils.getCsvLines("day6.csv");
        List<Integer> fishes = Arrays.stream(csvLines.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        HashMap<Integer, Integer> map = initMap();
        for (Integer fish : fishes) {
            int initialValue = map.get(fish);
            map.put(fish, initialValue + 1);
        }
        int newFishOnNewDay = map.get(0) * 2;
        for (int j = 0; j <= 82; j++) {
            System.out.println("Day " + j);
            for (int age = 0; age <= 8; age++) {
                if (age == 0) {
                    newFishOnNewDay = map.get(0) * 2;
                    map.put(0, map.get(1));
                } else if (age == 8) {
                    map.put(8, newFishOnNewDay);
                } else {
                    map.put(age, map.get(age + 1));
                }
            }

        }
        int counter = 0;
        for (int i = 0; i <= 8; i++) {
            counter += map.get(i);
        }
        System.out.println(counter);
    }

    private static HashMap<Integer, Integer> initMap() {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i <= 8; i++) {
            map.put(i, 0);
        }
        return map;
    }
}
