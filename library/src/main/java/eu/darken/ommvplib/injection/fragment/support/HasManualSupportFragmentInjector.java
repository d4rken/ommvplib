package eu.darken.ommvplib.injection.fragment.support;

import android.support.v4.app.Fragment;

import dagger.android.support.HasSupportFragmentInjector;
import eu.darken.ommvplib.injection.ManualInjector;

public interface HasManualSupportFragmentInjector extends HasSupportFragmentInjector {
    @Override
    ManualInjector<Fragment> supportFragmentInjector();
}
