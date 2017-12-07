package eu.darken.ommvplib.example.screens;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.injection.ComponentPresenter;


@MainComponent.Scope
public class MainPresenter extends ComponentPresenter<MainPresenter.View, MainComponent> {

    private final Class<? extends Fragment> startingFragment;
    private boolean doInit = true;
    private int bindCounter = 0;

    @Inject
    MainPresenter(Class<? extends Fragment> startingFragment) {
        this.startingFragment = startingFragment;
    }

    @Override
    public void onBindChange(@Nullable View view) {
        super.onBindChange(view);
        onView(v -> v.showBinderCounter(++bindCounter));
        onView(v -> {
            if (doInit) {
                doInit = false;
                v.showFragment(startingFragment);
            }
        });
    }

    public interface View extends Presenter.View {
        void showBinderCounter(int count);

        void showFragment(Class<? extends Fragment> fragmentClass);
    }
}
