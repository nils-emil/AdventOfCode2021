package day07;

import utils.FileUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TheTreacheryofWhales {

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        List<String> csvLines = FileUtils.getCsvLines("day7.csv");
        List<Integer> crabsPos = Arrays.stream(csvLines.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        Integer max = Collections.max(crabsPos);
        Integer min = Collections.min(crabsPos);

        Long leastFuelUsed = Long.MAX_VALUE;
        int bestPostX = 0;
        for (int x = min; x <= max; x++) {
            Long fuelNeeded = countFuel(x, crabsPos);
            if (fuelNeeded < leastFuelUsed) {
                leastFuelUsed = fuelNeeded;
                bestPostX = x;
            }
        }

        System.out.println(bestPostX);

    }

    private static Long countFuel(int x, List<Integer> crabsPos) {
        Long sum = 0L;
        for (Integer pos : crabsPos) {
            int temp = Math.abs( x - pos);
            for (int i = 0; i <= temp ; i++) {
                sum += i;
            }
        }
        return sum;
    }
}
