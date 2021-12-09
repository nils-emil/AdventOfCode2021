package day09;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9Part2 {
    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        List<String> csvLines = FileUtils.getCsvLines("day9.csv");
        String[][] finalData = csvLines.stream()
                .map(arr -> arr.split(""))
                .toArray(String[][]::new);
        List<Integer> basinSizes = new ArrayList<>();
        for (int x = 0; x < finalData[0].length; x++) {
            for (int y = 0; y < finalData.length; y++) {
                List<String> visited = new ArrayList<>();
                if (isLowSpot(finalData, x, y, Integer.parseInt(finalData[y][x]))) {
                    System.out.println("Low spot  " + x + ":" + y);
                    visited.add(x + ":" + y);
                    int basinSize = 1 + findBasin(x, y, visited, finalData);
                    System.out.println("Finished basin counting ... ");
                    basinSizes.add(basinSize);
                }
            }
        }
        Collections.sort(basinSizes);
        Collections.reverse(basinSizes);
        System.out.println(basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2));

    }

    private static int findBasin(int x, int y, List<String> visited, String[][] finalData) {
        int maxBasinCount = 0;
        for (int y1 = y - 1; y1 <= y + 1; y1++) {
            boolean isNewCoordinate = !visited.contains(x + ":" + y1);
            if (isWithInBounds(x, y, finalData)
                    && isWithInBounds(x, y1, finalData)
                    && isBasinFLowing(x, y, x, y1, finalData)
                    && isNewCoordinate) {
                System.out.println("Basin flows from " + x + ":" + y + " to " + +x + ":" + y1);
                visited.add(x + ":" + y1);
                int len = 1 + findBasin(x, y1, visited, finalData);
                maxBasinCount += len;
            }
        }
        for (int x1 = x - 1; x1 <= x + 1; x1++) {
            boolean isNewCoordinate = !visited.contains(x1 + ":" + y);
            if (isWithInBounds(x, y, finalData)
                    && isWithInBounds(x1, y, finalData)
                    && isBasinFLowing(x, y, x1, y, finalData)
                    && isNewCoordinate) {
                System.out.println("Basin flows from " + x + ":" + y + " to " + +x1 + ":" + y);
                visited.add(x1 + ":" + y);
                int len = 1 + findBasin(x1, y, visited, finalData);
                maxBasinCount += len;
            }
        }
        return maxBasinCount;
    }

    private static boolean isBasinFLowing(int x, int y, int x1, int y1, String[][] finalData) {
        int pos1 = Integer.parseInt(finalData[y][x]);
        int pos2 = Integer.parseInt(finalData[y1][x1]);
        return pos1 < pos2 && Math.abs(pos1 - pos2) == 1 && pos2 != 9;
    }

    private static boolean isWithInBounds(int x, int y, String[][] finalData) {
        boolean b = x >= 0 && y >= 0 && x < finalData[0].length && y < finalData.length;
        return b;
    }

    private static boolean isLowSpot(String[][] map, int x, int y, int value) {
        List<Integer> neigbours = new ArrayList<>();
        for (int x1 = x - 1; x1 <= x + 1; x1++) {
            for (int y1 = y - 1; y1 <= y + 1; y1++) {
                try {
                    if (y1 == y && x1 == x) {
                    } else {
                        Integer tempvalue = Integer.parseInt(map[y1][x1]);
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
        return true;
    }

}
