package eu.darken.ommvplib.injection.activity;

import android.app.Activity;

import dagger.MembersInjector;

public interface ActivityComponent<ActivityT extends Activity> extends MembersInjector<ActivityT> {
}
