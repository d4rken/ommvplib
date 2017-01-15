package eu.darken.ommvplib.example.screens.counting;

import dagger.Module;
import dagger.Provides;

@Module
public class CountingModule {

    @Provides
    @CountingScope
    Counter provideCounter() {
        return new Counter(-1);
    }

}
