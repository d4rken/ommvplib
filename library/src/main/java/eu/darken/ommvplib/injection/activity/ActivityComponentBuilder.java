package eu.darken.ommvplib.injection.activity;


import android.app.Activity;

public interface ActivityComponentBuilder<ActivityT extends Activity, ComponentT extends ActivityComponent<ActivityT>> {

    ComponentT build();
}
