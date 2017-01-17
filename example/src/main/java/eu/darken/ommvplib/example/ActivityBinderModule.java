package eu.darken.ommvplib.example;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import eu.darken.ommvplib.example.screens.MainActivity;
import eu.darken.ommvplib.example.screens.MainComponent;
import eu.darken.ommvplib.injection.activity.ActivityComponentBuilder;
import eu.darken.ommvplib.injection.activity.ActivityKey;

@Module(subcomponents = {MainComponent.class})
abstract class ActivityBinderModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract ActivityComponentBuilder mainActivity(MainComponent.Builder impl);
}