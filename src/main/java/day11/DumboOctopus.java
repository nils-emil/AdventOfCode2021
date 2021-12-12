package day11;

import utils.FileUtils;

import java.util.Arrays;
import java.util.List;

public class DumboOctopus {

    public static final int DAY_NR = 11;
    public static int NUMBER_OF_FLASHES = 0;

    public static void main(String[] args) {
        solve("day" + DAY_NR + ".csv");
//        solve("day" + DAY_NR + "_ex.csv");
    }

    private static void solve(String file) {
        List<String> csvLines = FileUtils.getCsvLines(file);
        int[][] map = csvLines.stream()
                .map(arr -> arr.split(""))
                .map(e -> Arrays.stream(e).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
        int flashes = 0;
        for (int i = 1; i <= 999; i++) {
            for (int x = 0; x < map[0].length; x++) {
                for (int y = 0; y < map.length; y++) {
                    map[y][x] = map[y][x] + 1;
                }
            }
            for (int x = 0; x < map[0].length; x++) {
                for (int y = 0; y < map.length; y++) {
                    int value = map[y][x];
                    if (value > 9) {
                        NUMBER_OF_FLASHES++;
                        map[y][x] = Integer.MIN_VALUE;
                        increaseNumberForNeightors(map, x, y);
                    }
                }
            }
            for (int x = 0; x < map[0].length; x++) {
                for (int y = 0; y < map.length; y++) {
                    int value = map[y][x];
                    if (value < 0) {
                        map[y][x] = 0;
                    }
                }
            }
            if (NUMBER_OF_FLASHES == 100) {
                System.out.println("Answer "  + i);
                break;
            }
            NUMBER_OF_FLASHES = 0;
        }


    }

    private static void increaseNumberForNeightors(int[][] map, int x, int y) {
        for (int x1 = x - 1; x1 <= x + 1; x1++) {
            for (int y1 = y - 1; y1 <= y + 1; y1++) {
                if (!isSamePos(x, x1, y, y1) && isWithInBounds(map, x1, y1)) {
                    map[y1][x1] = map[y1][x1] + 1;
                    if (map[y1][x1] > 9) {
                        NUMBER_OF_FLASHES++;
                        map[y1][x1] = Integer.MIN_VALUE;
                        increaseNumberForNeightors(map, x1, y1);
                    }
                }
            }
        }
    }

    private static boolean isSamePos(int x, int x1, int y, int y1) {
        return x == x1 && y == y1;
    }


    private static boolean isWithInBounds(int[][] map, int x, int y) {
        return x >= 0 && y >= 0 && x < map[0].length && y < map.length;
    }

}
