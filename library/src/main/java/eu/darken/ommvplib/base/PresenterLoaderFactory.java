package eu.darken.ommvplib.base;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;


public class PresenterLoaderFactory<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>> extends LoaderFactory<ViewT, PresenterT> {
    private final LoaderManager loaderManager;

    public PresenterLoaderFactory(Context context, LoaderManager manager, int loaderId, PresenterSource<PresenterT> factory) {
        super(context, loaderId, factory);
        this.loaderManager = manager;
    }

    public void load(@Nullable Bundle savedState, final Callback<ViewT, PresenterT> callback) {
        Loader<PresenterT> loader = loaderManager.getLoader(getLoaderId());
        if (loader instanceof PresenterLoader) {
            PresenterLoader<ViewT, PresenterT> presenterLoader = (PresenterLoader<ViewT, PresenterT>) loader;
            callback.onPresenterReady(presenterLoader.getPresenter());
        } else {
            loaderManager.initLoader(getLoaderId(), savedState, new LoaderManager.LoaderCallbacks<PresenterT>() {
                @Override
                public Loader<PresenterT> onCreateLoader(int id, Bundle args) {
                    return new PresenterLoader<>(getContext(), getFactory(), args);
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
