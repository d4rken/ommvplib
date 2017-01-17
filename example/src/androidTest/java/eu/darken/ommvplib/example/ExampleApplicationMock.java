package eu.darken.ommvplib.example;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

import eu.darken.ommvplib.injection.activity.ActivityComponentBuilder;

public class ExampleApplicationMock extends ExampleApplication {
    public void putActivityComponentBuilder(final ActivityComponentBuilder builder, Class<? extends Activity> cls) {
        Map<Class<? extends Activity>, Provider<ActivityComponentBuilder>> activityComponentBuilders = new HashMap<>(this.componentBuilders);
        activityComponentBuilders.put(cls, new Provider<ActivityComponentBuilder>() {
            @Override
            public ActivityComponentBuilder get() {
                return builder;
            }
        });
        this.componentBuilders = activityComponentBuilders;
    }
}
