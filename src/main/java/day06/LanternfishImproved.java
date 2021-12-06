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
        HashMap<Integer, Long> map = initMap();
        for (Integer fish : fishes) {
            Long initialValue = map.get(fish);
            map.put(fish, initialValue + 1L);
        }
        Long newFishOnNewDay = 0L;
        Long resettingFishes = 0L;
        for (int j = 0; j < 256; j++) {
            System.out.println("Day " + j + " number of fish " + countFish(map));
            for (int age = 0; age <= 8; age++) {
                if (age == 0) {
                    newFishOnNewDay = map.get(0);
                    resettingFishes = map.get(0);
                    map.put(0, map.get(1));
                } else if (age == 8) {
                    map.put(8, newFishOnNewDay);
                } else if (age == 6) {
                    map.put(age, map.get(age + 1) + resettingFishes);
                } else {
                    map.put(age, map.get(age + 1));
                }
            }
        }
        Long counter = countFish(map);
        System.out.println(counter);
    }

    private static Long countFish(HashMap<Integer, Long> map) {
        Long counter = 0L;
        for (int i = 0; i <= 8; i++) {
            counter += map.get(i);
        }
        return counter;
    }

    private static HashMap<Integer, Long> initMap() {
        HashMap<Integer, Long> map = new HashMap();
        for (int i = 0; i <= 8; i++) {
            map.put(i, 0L);
        }
        return map;
    }
}
