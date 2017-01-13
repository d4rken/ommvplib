package eu.darken.ommvplib.example.pager.shifting;

public class Shifter {

    private String text;

    public Shifter(String text) {
        this.text = text;
    }

    public String shift() {
        return text = text.charAt(text.length() - 1) + text.substring(0, text.length() - 1);
    }

    public String getCurrent() {
        return text;
    }

}
