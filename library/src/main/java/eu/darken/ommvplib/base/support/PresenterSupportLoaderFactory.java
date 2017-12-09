package eu.darken.ommvplib.base.support;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import eu.darken.ommvplib.base.LoaderFactory;
import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterSource;


public class PresenterSupportLoaderFactory<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>> extends LoaderFactory<ViewT, PresenterT> {

    private final LoaderManager loaderManager;

    public PresenterSupportLoaderFactory(Context context, LoaderManager manager, int loaderId, PresenterSource<PresenterT> factory) {
        super(context, loaderId, factory);
        this.loaderManager = manager;
    }

    @Override
    public void load(@Nullable Bundle savedState, final Callback<ViewT, PresenterT> callback) {
        Loader<PresenterT> loader = loaderManager.getLoader(getLoaderId());
        if (loader instanceof PresenterSupportLoader) {
            PresenterSupportLoader<ViewT, PresenterT> presenterLoader = (PresenterSupportLoader<ViewT, PresenterT>) loader;
            callback.onPresenterReady(presenterLoader.getPresenter());
        } else {
            loaderManager.initLoader(getLoaderId(), savedState, new LoaderManager.LoaderCallbacks<PresenterT>() {
                @Override
                public Loader<PresenterT> onCreateLoader(int id, Bundle args) {
                    return new PresenterSupportLoader<>(getContext(), getFactory(), args);
                }

                @Override
                public void onLoadFinished(Loader<PresenterT> loader, PresenterT presenter) {
                    callback.onPresenterReady(presenter);
                }

                @Override
                public void onLoaderReset(Loader<PresenterT> loader) {
                    callback.onPresenterDestroyed();
                }
            });
        }
    }

}
