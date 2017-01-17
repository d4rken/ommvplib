package eu.darken.ommvplib.example.screens;

import android.support.v4.app.LoaderManager;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.injection.ComponentPresenter;
import eu.darken.ommvplib.injection.ComponentPresenterFragment;
import eu.darken.ommvplib.injection.PresenterComponent;

public abstract class BasePagerFragment<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends PresenterComponent<ViewT, PresenterT>>
        extends ComponentPresenterFragment<ViewT, PresenterT, ComponentT> {

    @Override
    public LoaderManager getLoaderManager() {
        return getActivity().getSupportLoaderManager(); // use activity's loader manager inside viewpager fragments
    }

    @Override
    public int getLoaderId() {
        return getTag().hashCode(); // unique within viewpager
    }

}