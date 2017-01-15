package eu.darken.ommvplib.example;

import android.app.Application;


public class ExampleApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initDependencyInjection();
    }

    private void initDependencyInjection() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
