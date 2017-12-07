package eu.darken.ommvplib.extra;

public class Preconditions {
    public static <T> T checkNotNull(final T reference) {
        if (reference == null) throw new NullPointerException();
        return reference;
    }
}
