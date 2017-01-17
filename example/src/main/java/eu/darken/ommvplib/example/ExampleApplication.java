package eu.darken.ommvplib.example;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import eu.darken.ommvplib.injection.activity.ActivityComponent;
import eu.darken.ommvplib.injection.activity.ActivityComponentBuilder;
import eu.darken.ommvplib.injection.activity.ActivityComponentBuilderSource;
import timber.log.Timber;


public class ExampleApplication extends Application implements ActivityComponentBuilderSource {

    @Inject AppComponent appComponent;
    @Inject Map<Class<? extends Activity>, Provider<ActivityComponentBuilder>> componentBuilders;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build()
                .inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public <A extends Activity, B extends ActivityComponentBuilder<A, ? extends ActivityComponent<A>>> B getComponentBuilder(Class<A> activityKey) {
        //noinspection unchecked
        return (B) componentBuilders.get(activityKey).get();
    }

    public static ActivityComponentBuilderSource get(Context context) {
        return ((ActivityComponentBuilderSource) context.getApplicationContext());
    }
}
