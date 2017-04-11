package eu.darken.ommvplib.example.screens;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import eu.darken.ommvplib.example.screens.counting.CountingComponent;
import eu.darken.ommvplib.example.screens.counting.CountingFragment;
import eu.darken.ommvplib.example.screens.settings.SettingsComponent;
import eu.darken.ommvplib.example.screens.settings.SettingsFragment;

@Module(subcomponents = {
        CountingComponent.class,
        SettingsComponent.class
})
abstract class MainActivityFragmentBinderModule {

    @Binds
    @IntoMap
    @FragmentKey(CountingFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> counting(CountingComponent.Builder impl);

    @Binds
    @IntoMap
    @FragmentKey(SettingsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> settings(SettingsComponent.Builder impl);
}