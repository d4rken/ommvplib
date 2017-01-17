package eu.darken.ommvplib.injection.fragment;

import android.support.v4.app.Fragment;

import dagger.MembersInjector;

public interface FragmentComponent<FragmentT extends Fragment> extends MembersInjector<FragmentT> {
}
