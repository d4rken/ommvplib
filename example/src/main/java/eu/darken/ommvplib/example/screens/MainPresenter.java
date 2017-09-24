package eu.darken.ommvplib.example.screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import eu.darken.ommvplib.injection.ComponentPresenter;


@MainScope
public class MainPresenter extends ComponentPresenter<MainView, MainComponent> {
    private final List<MainPagerAdapter.FragmentObj> fragmentObjs;
    private int lastPagerItemPosition;

    @Inject
    MainPresenter(List<MainPagerAdapter.FragmentObj> fragmentObjs, int defaultStartPage) {
        this.fragmentObjs = fragmentObjs;
        this.lastPagerItemPosition = defaultStartPage;
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) lastPagerItemPosition = bundle.getInt("pos");
    }

    @Override
    public void onBindChange(@Nullable MainView view) {
        super.onBindChange(view);
        if (view != null) view.showFragments(fragmentObjs);
        if (view != null) view.showPagerItem(lastPagerItemPosition);
    }

    void onPagerItemSelected(int position) {
        lastPagerItemPosition = position;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("pos", lastPagerItemPosition);
    }
}
