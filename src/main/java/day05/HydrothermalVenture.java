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
            if (isHorizontalOrVertical(x1, x2, y1, y2)) {
                int xDiff = Math.max(x1, x2) - Math.min(x1, x2);
                int yDiff = Math.max(y1, y2) - Math.min(y1, y2);
                if (Objects.equals(y1, y2)) {
                    System.out.println("Filling horizontal Line"  + line);
                    int smallerX = Math.min(x1, x2);
                    int smallerY = Math.min(y1, y2);
                    fillHorizon(map, smallerX, smallerY, Math.abs(xDiff));
                }
                if (Objects.equals(x1, x2)) {
                    System.out.println("Filling vertical Line" + line);
                    int smallerX = Math.min(x1, x2);
                    int smallerY = Math.min(y1, y2);
                    fillVertical(map, smallerX, smallerY, Math.abs(yDiff));
                }
            }
        }
        System.out.println(map);
        int count = countHits(map);
    }

    private static int countHits(Integer[][] map) {
        int mapSize = MAP_SIZE;
        int count = 0;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] >= 2) {
                    count ++;
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
            System.out.println(x + "," + yCordinate+ " marked to "  + (initialCount + 1));
        }
        System.out.println("----------");
    }

    private static void fillHorizon(Integer[][] map, Integer x, Integer y, Integer xDiff) {
        for (int increment = 0; increment <= xDiff; increment++) {
            int xCordinate = x + increment;
            Integer initialCount = map[xCordinate][y];
            map[xCordinate][y] = initialCount + 1;
            System.out.println(xCordinate + "," + y+ " marked to "  + (initialCount + 1));
        }
        System.out.println("----------");

    }

    private static boolean isHorizontalOrVertical(Integer x1, Integer x2, Integer y1, Integer y2) {
        return Objects.equals(x1, x2) || Objects.equals(y1, y2);
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
