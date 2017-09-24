package eu.darken.ommvplib.example.screens.settings;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.injection.ComponentPresenter;

public class SettingsPresenter extends ComponentPresenter<SettingsPresenter.View, SettingsComponent> {
    @Inject
    public SettingsPresenter() {

    }

    @Override
    public void onBindChange(@Nullable View view) {
        super.onBindChange(view);
    }

    interface View extends Presenter.View {

    }
}
