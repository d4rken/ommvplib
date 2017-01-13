package eu.darken.ommvplib.example.pager.counting;

public class Counter {

    private int i;

    public int countUp() {
        return ++i;
    }

    public int getCurrent() {
        return i;
    }

}
