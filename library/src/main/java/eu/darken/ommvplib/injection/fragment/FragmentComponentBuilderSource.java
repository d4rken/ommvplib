package eu.darken.ommvplib.injection.fragment;

import android.support.v4.app.Fragment;

public interface FragmentComponentBuilderSource {
    <A extends Fragment, B extends FragmentComponentBuilder<A, ? extends FragmentComponent<A>>> B getComponentBuilder(Class<A> fragmentClass);
}