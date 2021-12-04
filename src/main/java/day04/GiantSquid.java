package day04;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class GiantSquid {

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
        List<Board> collect = boards.entrySet().stream().map(e -> new Board(e.getValue())).collect(Collectors.toList());


        List<Integer> checkedNumbers = new ArrayList<>();
        List<Integer> finalOut = new ArrayList<>();
        for (Integer num : bingoNumbers) {
            checkedNumbers.add(num);
            Optional<Board> boardStream = collect.stream().filter(e -> e.didBoardWin(checkedNumbers))
                    .findFirst();

            if (boardStream.isPresent()) {

                System.out.println(boardStream.get());
                Board board = boardStream.get();
                List<String[]> input1 = board.getInput();
                for (String[] e : input1) {
                    for (String x : e) {
                        if (!checkedNumbers.contains(Integer.parseInt(x))) {
                            finalOut.add(Integer.parseInt(x));
                        }
                    }
                }
                break;
            }
        }
        int sum = 0;
        for (Integer i : finalOut) {
            sum += i;
        }
        return sum * checkedNumbers.get(checkedNumbers.size() - 1);
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
