package eu.darken.ommvplib.example;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import eu.darken.ommvplib.example.screens.MainActivity;
import eu.darken.ommvplib.example.screens.MainComponent;

@Module(subcomponents = {MainComponent.class})
abstract class ActivityBinderModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> mainActivity(MainComponent.Builder impl);
}