package eu.darken.ommvplib.example.screens;


import dagger.Subcomponent;
import eu.darken.ommvplib.injection.PresenterComponent;

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent extends PresenterComponent<MainView, MainPresenter> {
}
