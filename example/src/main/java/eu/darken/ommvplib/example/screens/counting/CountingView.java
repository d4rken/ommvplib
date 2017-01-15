package eu.darken.ommvplib.example.screens.counting;

import eu.darken.ommvplib.Presenter;


public interface CountingView extends Presenter.View {
    void showText(String text);
}
