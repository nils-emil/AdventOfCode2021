package day04;

import java.util.List;

public class Board {
    private List<String[]> input;

    Board(List<String[]> input) {
        this.input = input;
    }

    public List<String[]> getInput() {
        return input;
    }

    boolean didBoardWin(List<Integer> checkedNumbers) {
        // rows
        for (int i = 0; i < 5; i++) {
            int counter = 0;
            for (int j = 0; j < 5; j++) {
                if (checkedNumbers.contains(Integer.parseInt(input.get(i)[j]))) {
                    counter++;
                }
            }
            if (counter == 5) {
                return true;
            }
        }

        // cols
        for (int i = 0; i < 5; i++) {
            int counter = 0;
            for (int j = 0; j < 5; j++) {
                if (checkedNumbers.contains(Integer.parseInt(input.get(j)[i]))) {
                    counter++;
                }
            }
            if (counter == 5) {
                return true;
            }
        }

        int counter1 = 0;
        // diagonal 1
        for (int i = 0; i < 5; i++) {
            if (checkedNumbers.contains(Integer.parseInt(input.get(i)[i]))) {
                counter1++;
            }
        }
        if (counter1 == 5) {
            return true;
        }

        int counter2 = 0;
        // diagonal 2
        for (int i = 0; i < 5; i++) {
            if (checkedNumbers.contains(Integer.parseInt(input.get(4-i)[4 -i]))) {
                counter2++;
            }
        }
        if (counter2 == 5) {
            return true;
        }

        return false;
    }

}
