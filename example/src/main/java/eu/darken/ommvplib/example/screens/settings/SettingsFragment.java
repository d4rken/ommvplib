package eu.darken.ommvplib.example.screens.settings;

import android.os.Bundle;

import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.injection.ComponentPresenterPreferenceFragment;

public class SettingsFragment extends ComponentPresenterPreferenceFragment<SettingsPresenter.View, SettingsPresenter, SettingsComponent>
        implements SettingsPresenter.View {
    @Override
    public SettingsComponent createComponent() {
        SettingsComponent.Builder builder = getComponentBuilder(this);
        return builder.build();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public Class<? extends SettingsPresenter> getTypeClazz() {
        return SettingsPresenter.class;
    }
}
