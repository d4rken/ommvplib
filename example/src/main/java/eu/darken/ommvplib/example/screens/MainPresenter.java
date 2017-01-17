package eu.darken.ommvplib.example.screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import eu.darken.ommvplib.injection.ComponentPresenter;


@MainScope
public class MainPresenter extends ComponentPresenter<MainView, MainComponent> {

    private int lastPagerItemPosition;

    @Inject
    MainPresenter(int defaultStartPage) {
        this.lastPagerItemPosition = defaultStartPage;
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        if (bundle != null) lastPagerItemPosition = bundle.getInt("pos");
    }

    @Override
    public void onBindChange(@Nullable MainView view) {
        if (view != null) view.showPagerItem(lastPagerItemPosition);
    }

    void onPagerItemSelected(int position) {
        lastPagerItemPosition = position;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putInt("pos", lastPagerItemPosition);
    }

    @Override
    public void onDestroy() {

    }
}
