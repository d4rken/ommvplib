package eu.darken.ommvplib.example.screens;

import java.util.List;

import eu.darken.ommvplib.base.Presenter;

public interface MainView extends Presenter.View {
    void showPagerItem(int position);

    void showFragments(List<MainPagerAdapter.FragmentObj> fragmentbjects);
}
