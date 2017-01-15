package eu.darken.ommvplib.example.screens;

import android.support.v4.app.LoaderManager;

import eu.darken.ommvplib.BaseView;
import eu.darken.ommvplib.injection.BaseComponent;
import eu.darken.ommvplib.injection.ComponentPresenter;
import eu.darken.ommvplib.injection.ComponentPresenterFragment;

public abstract class BasePagerFragment<
        ViewT extends BaseView,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends BaseComponent<ViewT, PresenterT>>
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
