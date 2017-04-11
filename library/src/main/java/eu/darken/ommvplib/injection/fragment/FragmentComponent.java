package eu.darken.ommvplib.injection.fragment;

import android.app.Fragment;
import android.support.annotation.Nullable;

import dagger.android.AndroidInjector;

public interface FragmentComponent<FragmentT extends Fragment> extends AndroidInjector<FragmentT> {

    abstract class Builder<FragmentT extends Fragment, ComponentT extends FragmentComponent<FragmentT>>
            extends AndroidInjector.Builder<FragmentT> {
        public abstract ComponentT build();

        @Override
        public void seedInstance(@Nullable FragmentT instance) {

        }
    }
}
