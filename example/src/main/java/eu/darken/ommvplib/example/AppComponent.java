package eu.darken.ommvplib.example;


import android.app.Activity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import eu.darken.ommvplib.injection.ComponentSource;

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBinderModule.class,
        AndroidInjectionModule.class
})
public interface AppComponent {

    ExampleApplication inject(ExampleApplication application);

    ComponentSource<Activity> activityComponentSource();

    @Component.Builder
    interface Builder {
        Builder appModule(AppModule module);

        AppComponent build();
    }

}
