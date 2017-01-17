package eu.darken.ommvplib.example.screens.counting;

import eu.darken.ommvplib.base.Presenter;


public interface CountingView extends Presenter.View {
    void showText(String text);
}
