package eu.darken.ommvplib.example.screens;

import android.support.v4.app.LoaderManager;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.injection.ComponentPresenter;
import eu.darken.ommvplib.injection.ComponentPresenterSupportFragment;
import eu.darken.ommvplib.injection.PresenterComponent;
import eu.darken.ommvplib.injection.fragment.support.SupportFragmentComponent;

public abstract class BasePagerFragment<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends SupportFragmentComponent & PresenterComponent<ViewT, PresenterT>>
        extends ComponentPresenterSupportFragment<ViewT, PresenterT, ComponentT> {

    @Override
    public LoaderManager getLoaderManager() {
        return getActivity().getSupportLoaderManager(); // use activity's loader manager inside viewpager fragments
    }

    @Override
    public int getLoaderId() {
        return getTag().hashCode(); // unique within viewpager
    }

}
