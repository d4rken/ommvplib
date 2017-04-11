package eu.darken.ommvplib.example.screens;


import dagger.Subcomponent;
import dagger.android.support.AndroidSupportInjectionModule;
import eu.darken.ommvplib.injection.PresenterComponent;
import eu.darken.ommvplib.injection.activity.ActivityComponent;

@MainScope
@Subcomponent(modules = {
        MainActivityFragmentBinderModule.class,
        AndroidSupportInjectionModule.class,
        MainModule.class
})
public interface MainComponent extends ActivityComponent<MainActivity>, PresenterComponent<MainView, MainPresenter> {

    @Subcomponent.Builder
    abstract class Builder extends ActivityComponent.Builder<MainActivity, MainComponent> {
    }
}
