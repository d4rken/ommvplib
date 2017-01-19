package eu.darken.ommvplib.example.screens.settings;

import dagger.Subcomponent;
import eu.darken.ommvplib.injection.PresenterComponent;
import eu.darken.ommvplib.injection.fragment.FragmentComponent;
import eu.darken.ommvplib.injection.fragment.FragmentComponentBuilder;

@Subcomponent
public interface SettingsComponent extends FragmentComponent<SettingsFragment>, PresenterComponent<SettingsPresenter.View, SettingsPresenter> {
    @Subcomponent.Builder
    interface Builder extends FragmentComponentBuilder<SettingsFragment, SettingsComponent> {

    }
}
