package day04;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class GiantSquid2 {

    public static void main(String[] args) {
        day1();
    }

    private static int day1() {
        List<String> input = getCsvLines("day4.csv");
        List<Integer> bingoNumbers = Arrays.asList(input.get(0).split(",")).stream().map(e -> Integer.parseInt(e)).collect(Collectors.toList());
        input.remove(0);
        HashMap<Integer, List<String[]>> boards = new HashMap<>();
        int boardNumber = 0;
        while (true) {
            if (input.isEmpty()) {
                break;
            }
            String s = input.get(0);
            if (s.isEmpty()) {
                boardNumber++;
                boards.put(boardNumber, new ArrayList<>());
            } else {

                List<String[]> strings = boards.get(boardNumber);
                String replace = s.replace("  ", " ");
                if (replace.startsWith(" ")) {
                    replace = replace.substring(1);
                }
                strings.add(replace.split(" "));
            }
            input.remove(0);
        }
        List<Board> allBoards = boards.entrySet().stream().map(e -> new Board(e.getValue())).collect(Collectors.toList());


        List<Integer> checkedNumbers = new ArrayList<>();
        Board lastWinner = null;
        List<Integer> lastWinnerNumbers = new ArrayList<>();
        for (Integer num : bingoNumbers) {
            checkedNumbers.add(num);
            List<Board> boardStream = allBoards.stream().filter(e -> e.didBoardWin(checkedNumbers)).collect(Collectors.toList());
            for (Board b : boardStream) {
                allBoards.remove(b);
                lastWinner = b;
                lastWinnerNumbers = new ArrayList<>(checkedNumbers);
            }
        }

        List<Integer> finalOut = new ArrayList<>();
        for (String[] e : lastWinner.getInput()) {
            for (String x : e) {
                if (!lastWinnerNumbers.contains(Integer.parseInt(x))) {
                    finalOut.add(Integer.parseInt(x));
                }
            }
        }
        int sum = 0;
        for (Integer i : finalOut) {
            sum += i;
        }
        return sum * lastWinnerNumbers.get(lastWinnerNumbers.size() - 1);
    }

    public static List<String> getCsvLines(String filename) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = classloader.getResourceAsStream(filename);
        List<String> names = new ArrayList<>();
        try (Scanner scanner = new Scanner(resourceAsStream)) {
            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine());
            }
        }
        return names;
    }
}
