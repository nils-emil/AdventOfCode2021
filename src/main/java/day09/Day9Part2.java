package day09;

import utils.FileUtils;

import java.util.*;

public class Day9Part2 {
    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        List<String> csvLines = FileUtils.getCsvLines("day9.csv");
        Integer[][] map = new Integer[csvLines.size()][csvLines.get(0).length()];
        int counter = 0;
        String[][] finalData = csvLines.stream()
                .map(arr -> arr.split(""))
                .toArray(String[][]::new);
        List<Integer> basinLens = new ArrayList<>();
        for (int x = 0; x < finalData[0].length; x++) {
            for (int y = 0; y < finalData.length; y++) {
                List<String> usedCoordinates = new ArrayList<>();
                int len = findBasin(x, y, usedCoordinates, finalData);
                basinLens.add(len);
            }
        }
        Collections.sort(basinLens);
        Collections.reverse(basinLens);

        System.out.println(map);

    }

    private static int findBasin(int x, int y, List<String> usedCoordinates, String[][] finalData) {
        int maxBasinCount = 0;
        for (int x1 = x - 1; x1 <= x + 1; x1++) {
            for (int y1 = y - 1; y1 <= y + 1; y1++) {
                boolean isNewCoordinate = !usedCoordinates.contains(x + ":" + y);
                if (isWithInBounds(x, y, finalData)
                        && isWithInBounds(x1, y1, finalData)
                        && isBasinFLowing(x, y, x1, y1, finalData)
                        && isNewCoordinate) {
                    List<String> coordinas = new ArrayList<>(usedCoordinates);
                    coordinas.add(x +":" + y);
                    int len = 1 + findBasin(x1, y1, coordinas, finalData);
                    if (len > maxBasinCount) {
                        maxBasinCount = len;
                    }
                }
            }
        }
        return maxBasinCount;
    }

    private static boolean isBasinFLowing(int x, int y, int x1, int y1, String[][] finalData) {
        return Integer.parseInt(finalData[y][x]) > Integer.parseInt(finalData[y1][x1]);
    }

    private static boolean isWithInBounds(int x, int y, String[][] finalData) {
        boolean b = x >= 0 && y >= 0 && x < finalData[0].length && y < finalData.length;
        return b;
    }


}
