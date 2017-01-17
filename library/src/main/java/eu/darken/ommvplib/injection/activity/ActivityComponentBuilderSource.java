package eu.darken.ommvplib.injection.activity;

import android.app.Activity;

public interface ActivityComponentBuilderSource {
    <ActivityT extends Activity, BuilderT extends ActivityComponentBuilder<ActivityT, ? extends ActivityComponent<ActivityT>>>
    BuilderT getComponentBuilder(Class<ActivityT> activityClass);
}