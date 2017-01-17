package eu.darken.ommvplib.example;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBinderModule.class
})
public interface AppComponent {

    ExampleApplication inject(ExampleApplication application);

    @Component.Builder
    interface Builder {
        Builder appModule(AppModule module);

        AppComponent build();
    }

}
