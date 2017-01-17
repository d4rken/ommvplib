package eu.darken.ommvplib.injection.fragment;


import android.support.v4.app.Fragment;

public interface FragmentComponentBuilder<FragmentT extends Fragment, ComponentT extends FragmentComponent<FragmentT>> {
    ComponentT build();
}
