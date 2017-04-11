package eu.darken.ommvplib.injection.service;


import android.app.Service;

import dagger.android.HasServiceInjector;
import eu.darken.ommvplib.injection.ManualInjector;

public interface HasManualServiceInjector extends HasServiceInjector {
    @Override
    ManualInjector<Service> serviceInjector();
}
