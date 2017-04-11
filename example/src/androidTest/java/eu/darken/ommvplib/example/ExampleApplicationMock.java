package eu.darken.ommvplib.example;

import android.app.Activity;

import eu.darken.ommvplib.injection.ManualInjector;

public class ExampleApplicationMock extends ExampleApplication {

    public void setActivityComponentSource(ManualInjector<Activity> injector) {
        this.manualInjector = injector;
    }
}
