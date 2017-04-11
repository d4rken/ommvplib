package eu.darken.ommvplib.example.screens.settings;

import dagger.Subcomponent;
import eu.darken.ommvplib.injection.PresenterComponent;
import eu.darken.ommvplib.injection.fragment.support.SupportFragmentComponent;

@Subcomponent
public interface SettingsComponent extends SupportFragmentComponent<SettingsFragment>, PresenterComponent<SettingsPresenter.View, SettingsPresenter> {
    @Subcomponent.Builder
    abstract class Builder extends SupportFragmentComponent.Builder<SettingsFragment, SettingsComponent> {

    }
}
