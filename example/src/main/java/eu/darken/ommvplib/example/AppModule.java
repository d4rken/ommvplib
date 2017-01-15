package eu.darken.ommvplib.example;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {

    private final ExampleApplication application;

    AppModule(ExampleApplication application) {
        this.application = application;
    }

    @Provides
    ExampleApplication provideApplication() {
        return application;
    }

}
