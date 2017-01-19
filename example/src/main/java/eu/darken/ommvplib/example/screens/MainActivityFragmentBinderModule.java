package eu.darken.ommvplib.example.screens;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import eu.darken.ommvplib.example.screens.counting.CountingComponent;
import eu.darken.ommvplib.example.screens.counting.CountingFragment;
import eu.darken.ommvplib.example.screens.settings.SettingsComponent;
import eu.darken.ommvplib.example.screens.settings.SettingsFragment;
import eu.darken.ommvplib.injection.fragment.FragmentComponentBuilder;
import eu.darken.ommvplib.injection.fragment.FragmentKey;

@Module(subcomponents = {
        CountingComponent.class,
        SettingsComponent.class
})
abstract class MainActivityFragmentBinderModule {

    @Binds
    @IntoMap
    @FragmentKey(CountingFragment.class)
    abstract FragmentComponentBuilder counting(CountingComponent.Builder impl);

    @Binds
    @IntoMap
    @FragmentKey(SettingsFragment.class)
    abstract FragmentComponentBuilder settings(SettingsComponent.Builder impl);
}