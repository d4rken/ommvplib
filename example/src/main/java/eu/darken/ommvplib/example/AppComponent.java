package eu.darken.ommvplib.example;


import javax.inject.Singleton;

import dagger.Component;
import eu.darken.ommvplib.example.screens.MainComponent;
import eu.darken.ommvplib.example.screens.MainModule;
import eu.darken.ommvplib.example.screens.counting.CountingComponent;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    MainComponent mainComponent(MainModule mainModule);

    CountingComponent countingComponent();

}
