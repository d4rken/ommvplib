package eu.darken.ommvplib.example.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import eu.darken.ommvplib.example.ExampleApplication;
import eu.darken.ommvplib.injection.ComponentPresenter;


@MainScope
public class MainPresenter extends ComponentPresenter<MainView, MainComponent> {

    @Inject ExampleApplication application;

    private int lastPagerItemPosition = 0;

    @Inject
    MainPresenter() {
        // ;
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {

    }

    @Override
    public void onBind(@Nullable MainView view) {
        if (view != null) view.showPagerItem(lastPagerItemPosition);
    }

    void onPagerItemSelected(int position) {
        lastPagerItemPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

    @Override
    public void onDestroy() {

    }
}
