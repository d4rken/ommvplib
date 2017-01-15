package eu.darken.ommvplib.example.screens.counting;

public class Counter {

    private int i;

    public Counter(int i) {
        this.i = i;
    }

    void setCounter(int i) {
        this.i = i;
    }

    int countUp() {
        return ++i;
    }

    int getCurrent() {
        return i;
    }

}
