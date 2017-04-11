package eu.darken.ommvplib.example;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import eu.darken.ommvplib.injection.ManualInjector;
import eu.darken.ommvplib.injection.activity.HasManualActivityInjector;
import timber.log.Timber;


public class ExampleApplication extends Application implements HasManualActivityInjector {

    AppComponent appComponent;
    ManualInjector<Activity> manualInjector;
    RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) return;
        refWatcher = LeakCanary.install(this);

        Timber.plant(new Timber.DebugTree());
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        manualInjector = appComponent.activityComponentSource();
    }

    public static RefWatcher getRefWatcher(Context context) {
        ExampleApplication application = (ExampleApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public ManualInjector<Activity> activityInjector() {
        return manualInjector;
    }
}
