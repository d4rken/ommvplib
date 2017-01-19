package eu.darken.ommvplib.example.screens.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.injection.ComponentPresenter;

public class SettingsPresenter extends ComponentPresenter<SettingsPresenter.View, SettingsComponent> {
    @Inject
    public SettingsPresenter() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onBindChange(@Nullable View view) {
        super.onBindChange(view);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onDestroy() {

    }

    interface View extends Presenter.View {

    }
}
