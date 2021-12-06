package day06;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lanternfish {

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        List<String> csvLines = FileUtils.getCsvLines("day6.csv");
        List<Fish> fishes = Arrays.stream(csvLines.get(0).split(",")).map(Integer::parseInt).map(e -> new Fish(e)).collect(Collectors.toList());
        List<Fish> newFishes = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            for (Fish fish : fishes) {
                Fish tick = fish.tick(fishes);
                if (tick != null) {
                    newFishes.add(tick);
                }
            }
            fishes.addAll(newFishes);
            newFishes = new ArrayList<>();
        }
        System.out.println(fishes.size());
    }
}
