package eu.darken.ommvplib.injection;

import dagger.android.AndroidInjector;

public interface ManualInjector<T> extends AndroidInjector<T> {

    AndroidInjector<T> get(T instance);
}
