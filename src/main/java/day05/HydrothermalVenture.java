package day05;

import utils.FileUtils;

import java.util.List;
import java.util.Objects;

public class HydrothermalVenture {

    public static final int MAP_SIZE = 1000;

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        List<String> csvLines = FileUtils.getCsvLines("day5.csv");
        Integer[][] map = initMap();
        for (String line : csvLines) {
            String[] split = line.split(" -> ");
            String[] cordinateFrom = split[0].split(",");
            String[] cordinateTo = split[1].split(",");
            int x1 = Integer.parseInt(cordinateFrom[0]);
            int x2 = Integer.parseInt(cordinateTo[0]);
            int y1 = Integer.parseInt(cordinateFrom[1]);
            int y2 = Integer.parseInt(cordinateTo[1]);
            int xDiff = Math.max(x1, x2) - Math.min(x1, x2);
            int yDiff = Math.max(y1, y2) - Math.min(y1, y2);
            int smallerX = Math.min(x1, x2);
            int smallerY = Math.min(y1, y2);
            if (Objects.equals(y1, y2)) {
                fillHorizon(map, smallerX, smallerY, Math.abs(xDiff));
            } else if (Objects.equals(x1, x2)) {
                fillVertical(map, smallerX, smallerY, Math.abs(yDiff));
            } else {
                fillDiagonal(map, x1, x2, y1, y2, Math.abs(xDiff), Math.abs(yDiff), line);
            }
        }
        int count = countHits(map);
        System.out.println(count);
        for (int i = 0; i < MAP_SIZE; i++) {
            for (Integer[] arr : map) {
                if (arr[i] == 0) {
                    System.out.print("-");
                } else {
                    System.out.print(arr[i]);
                }

            }
            System.out.println();
        }
    }

    private static void fillDiagonal(Integer[][] map, int x1, int x2, int y1, int y2, int abs, int abs1, String line) {
        if (x1 < x2 && y2 < y1) {
            System.out.println("From bottom left to up right " + line);
            fillDiagonalUpVol2(map, x1, y2, abs, line);
        } else if (x1 < x2 && y2 > y1) {
            System.out.println("From upper left to down right " + line);
            fillDiagonalUp(map, x1, y1, abs, line);
        } else if (x1 > x2 && y2 < y1) {
            System.out.println("From bottom right to up left " + line);
            fillDiagonalUp(map, x2, y2, abs, line);
        } else if (x1 > x2 && y2 > y1) {
            System.out.println("From upper right to down left " + line);
            fillDiagonalUpVol2(map, x2, y1, abs, line);
        }
    }

    private static void fillDiagonalUpVol2(Integer[][] map, int x, int y, int moveXTo, String line) {
        for (int i = 0; i <= moveXTo; i++) {
            int newX = x + i;
            int newY = y + (moveXTo - i);
            Integer initialCount = map[newX][newY];
            map[newX][newY] = initialCount + 1;
            System.out.println(newX + "," + newY + " marked to " + (initialCount + 1));
        }
    }

    private static void fillDiagonalUp(Integer[][] map, int x, int y, int moveXTo, String line) {
        for (int i = 0; i <= moveXTo; i++) {
            int newX = x + i;
            int newY = y + i;
            Integer initialCount = map[newX][newY];
            map[newX][newY] = initialCount + 1;
            System.out.println(newX + "," + newY + " marked to " + (initialCount + 1));
        }
    }

    private static int countHits(Integer[][] map) {
        int mapSize = MAP_SIZE;
        int count = 0;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] >= 2) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void fillVertical(Integer[][] map, Integer x, Integer y, Integer yDiff) {
        for (int increment = 0; increment <= yDiff; increment++) {
            int yCordinate = y + increment;
            Integer initialCount = map[x][yCordinate];
            map[x][yCordinate] = initialCount + 1;
        }
    }

    private static void fillHorizon(Integer[][] map, Integer x, Integer y, Integer xDiff) {
        for (int increment = 0; increment <= xDiff; increment++) {
            int xCordinate = x + increment;
            Integer initialCount = map[xCordinate][y];
            map[xCordinate][y] = initialCount + 1;
        }

    }

    private static Integer[][] initMap() {
        int mapSize = MAP_SIZE;
        Integer[][] map = new Integer[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = 0;
            }
        }
        return map;
    }


}
