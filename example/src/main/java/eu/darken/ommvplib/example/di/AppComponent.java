package eu.darken.ommvplib.example.di;


import javax.inject.Singleton;

import dagger.Component;
import eu.darken.ommvplib.example.main.MainComponent;
import eu.darken.ommvplib.example.main.MainModule;
import eu.darken.ommvplib.example.pager.counting.CountingComponent;
import eu.darken.ommvplib.example.pager.shifting.ShiftingComponent;
import eu.darken.ommvplib.example.pager.shifting.ShiftingModule;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    MainComponent mainComponent(MainModule mainModule);

    CountingComponent countingComponent();

    ShiftingComponent shiftingComponent(ShiftingModule shiftingModule);

}
