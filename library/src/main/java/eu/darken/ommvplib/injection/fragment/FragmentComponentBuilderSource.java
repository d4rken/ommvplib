package eu.darken.ommvplib.injection.fragment;

import android.support.v4.app.Fragment;

public interface FragmentComponentBuilderSource {
    <FragmentT extends Fragment, BuilderT extends FragmentComponentBuilder<FragmentT, ? extends FragmentComponent<FragmentT>>>
    BuilderT getComponentBuilder(Class<FragmentT> fragmentClass);
}