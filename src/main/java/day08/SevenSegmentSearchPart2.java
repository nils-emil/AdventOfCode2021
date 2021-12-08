package day08;

import utils.FileUtils;

import java.util.*;

import static java.util.Arrays.sort;

public class SevenSegmentSearchPart2 {

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        List<String> csvLines = FileUtils.getCsvLines("day8.csv");
        List<Integer> uniqueNumbers = new ArrayList();

        uniqueNumbers.add(2); // 1
        uniqueNumbers.add(3); // 7
        uniqueNumbers.add(4); // 4
        uniqueNumbers.add(7); // 8
        int counter = 0;
        for (String line : csvLines) {
            String[] split = line.split(" \\| ");
            HashMap<Integer, String> numbers = getNumbers(line);
            HashMap<String, Integer> numbersReverse = reverese(numbers);
            String[] output = split[1].split(" ");
            String rowResult = "";
            for (String number : output) {
                char[] a = number.toCharArray();
                sort(a);
                String e = new String(a);
                rowResult = rowResult + numbersReverse.get(e).toString();
            }
            counter += Integer.parseInt(rowResult);
        }

        System.out.println(counter);
    }

    private static HashMap<String, Integer> reverese(HashMap<Integer, String> numbers) {
        HashMap<String, Integer> reversedMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : numbers.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            reversedMap.put(value, key);
        }
        return reversedMap;
    }


    private static HashMap<Integer, String> getNumbers(String line) {
        HashMap<Integer, List<String>> numbersBucket =  new HashMap();
        numbersBucket.put(2, new ArrayList<>()); // 1
        numbersBucket.put(3, new ArrayList<>()); // 7
        numbersBucket.put(4, new ArrayList<>()); // 4
        numbersBucket.put(6, new ArrayList<>()); // 0, 6, 9
        numbersBucket.put(5, new ArrayList<>()); // 2. 3, 5
        numbersBucket.put(7, new ArrayList<>()); // 8
        String fullLine = line.replace(" | ", " ");
        List<String> numbers = Arrays.asList(fullLine.split(" "));
        for (String num : numbers) {
            List<String> list = numbersBucket.get(num.length());
            char[] a = num.toCharArray();
            sort(a);
            String e = new String(a);
            if (!list.contains(e)) {
                list.add(e);
            }
        }
        HashMap<Integer, String> realNumbers = new HashMap<>();
        realNumbers.put(1, numbersBucket.get(2).get(0));
        realNumbers.put(7, numbersBucket.get(3).get(0));
        realNumbers.put(4, numbersBucket.get(4).get(0));
        realNumbers.put(8, numbersBucket.get(7).get(0));
        for (int i = 0; i < 3; i++) {
            int i1 = mapToNumber(numbersBucket, numbersBucket.get(6).get(i));
            realNumbers.put(i1, numbersBucket.get(6).get(i));
            int i2 = mapToNumber(numbersBucket, numbersBucket.get(5).get(i));
            realNumbers.put(i2, numbersBucket.get(5).get(i));
        }
        return realNumbers;
    }

    private static int mapToNumber(HashMap<Integer, List<String>> numbersBucket, String num) {
        char[] one = numbersBucket.get(2).get(0).toCharArray();
        char[] four = numbersBucket.get(4).get(0).toCharArray();
        char[] seven = numbersBucket.get(7).get(0).toCharArray();
        char[] eight = numbersBucket.get(3).get(0).toCharArray();
        int interSectionsWith1 = getIntersection(one, num.toCharArray());
        int interSectionsWith4 = getIntersection(four, num.toCharArray());
        int interSectionsWith7 = getIntersection(seven, num.toCharArray());
        int interSectionsWith8 = getIntersection(eight, num.toCharArray());
        if (interSectionsWith1 == 1 && interSectionsWith4 == 3 && interSectionsWith7 == 6 && interSectionsWith8 == 2) {
            return 6;
        }
        if (interSectionsWith1 == 2 && interSectionsWith4 == 4 && interSectionsWith7 == 6 && interSectionsWith8 == 3) {
            return 9;
        }
        if (interSectionsWith1 == 2 && interSectionsWith4 == 3 && interSectionsWith7 == 6 && interSectionsWith8 == 3) {
            return 0;
        }

        if (interSectionsWith1 == 2 && interSectionsWith4 == 3 && interSectionsWith7 == 5 && interSectionsWith8 == 3) {
            return 3;
        }
        if (interSectionsWith1 == 1 && interSectionsWith4 == 3 && interSectionsWith7 == 5 && interSectionsWith8 == 2) {
            return 5;
        }
        if (interSectionsWith1 == 1 && interSectionsWith4 == 2 && interSectionsWith7 == 5 && interSectionsWith8 == 2) {
            return 2;
        }
        return 0;
    }

    public static int getIntersection(char[] arr1, char[] arr2) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    list.add(arr1[i]);
                }
            }
        }
        return list.toArray().length;
    }

}
