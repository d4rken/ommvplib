package eu.darken.ommvplib.example;

import android.app.Activity;
import android.support.v4.app.Fragment;


public class InjectionHelper {

    public static AppComponent getAppComponent(Fragment fragment) {
        return getAppComponent(fragment.getActivity());
    }

    public static AppComponent getAppComponent(Activity activity) {
        return ((ExampleApplication) activity.getApplication()).getAppComponent();
    }

}
