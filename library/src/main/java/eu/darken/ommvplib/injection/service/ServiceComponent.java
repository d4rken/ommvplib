package eu.darken.ommvplib.injection.service;

import android.app.Service;
import android.support.annotation.Nullable;

import dagger.android.AndroidInjector;

public interface ServiceComponent<ServiceT extends Service> extends AndroidInjector<ServiceT> {
    abstract class Builder<ServiceT extends Service, ComponentT extends ServiceComponent<ServiceT>>
            extends AndroidInjector.Builder<ServiceT> {
        public abstract ComponentT build();

        @Override
        public void seedInstance(@Nullable ServiceT instance) {

        }
    }
}
