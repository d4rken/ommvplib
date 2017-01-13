package eu.darken.ommvplib.example.di;

import dagger.Module;
import dagger.Provides;
import eu.darken.ommvplib.example.ExampleApplication;

@Module
public class AppModule {

    private final ExampleApplication application;

    public AppModule(ExampleApplication application) {
        this.application = application;
    }

    @Provides
    ExampleApplication provideApplication() {
        return application;
    }

}
