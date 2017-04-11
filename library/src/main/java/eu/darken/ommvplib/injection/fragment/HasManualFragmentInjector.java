package eu.darken.ommvplib.injection.fragment;


import android.app.Fragment;

import dagger.android.HasFragmentInjector;
import eu.darken.ommvplib.injection.ManualInjector;

public interface HasManualFragmentInjector extends HasFragmentInjector {
    @Override
    ManualInjector<Fragment> fragmentInjector();
}
