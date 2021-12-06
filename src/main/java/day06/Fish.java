package day06;

import java.util.List;

public class Fish {
    private int counter;

    public Fish(int counter) {
        this.counter = counter;
    }

    public Fish tick(List<Fish> fishes)  {
        if (this.counter == 0) {
            this.counter = 6;
            return new Fish(8);
        }
        this.counter--;
        return null;
    }
}
