package eu.darken.ommvplib.example.screens;

import eu.darken.ommvplib.base.Presenter;

public interface MainView extends Presenter.View {
    void showPagerItem(int position);
}
