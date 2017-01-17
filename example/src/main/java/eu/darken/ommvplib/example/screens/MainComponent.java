package eu.darken.ommvplib.example.screens;


import dagger.Subcomponent;
import eu.darken.ommvplib.injection.PresenterComponent;
import eu.darken.ommvplib.injection.activity.ActivityComponent;
import eu.darken.ommvplib.injection.activity.ActivityComponentBuilder;

@MainScope
@Subcomponent(modules = {
        MainModule.class,
        MainActivityFragmentBinderModule.class
})
public interface MainComponent extends ActivityComponent<MainActivity>, PresenterComponent<MainView, MainPresenter> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<MainActivity, MainComponent> {
        Builder activityModule(MainModule module);
    }
}
