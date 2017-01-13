package eu.darken.ommvplib.example.main;


import dagger.Subcomponent;
import eu.darken.ommvplib.injection.BaseComponent;

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent extends BaseComponent<MainView, MainPresenter> {
}
