package eu.darken.ommvplib.injection.activity;

import android.app.Activity;

import dagger.android.HasActivityInjector;
import eu.darken.ommvplib.injection.ManualInjector;

public interface HasManualActivityInjector extends HasActivityInjector {
    @Override
    ManualInjector<Activity> activityInjector();
}
