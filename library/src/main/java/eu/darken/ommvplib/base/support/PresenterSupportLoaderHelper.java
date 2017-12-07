package eu.darken.ommvplib.base.support;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterFactory;


public class PresenterSupportLoaderHelper<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>> {

    private final LoaderManager loaderManager;
    @Nullable private final Bundle savedState;
    private final Context context;

    public PresenterSupportLoaderHelper(Context context, LoaderManager manager, @Nullable Bundle savedState) {
        this.context = context;
        this.loaderManager = manager;
        this.savedState = savedState;
    }

    public void fetch(final PresenterFactory<PresenterT> factory, int loaderId, final Callback<ViewT, PresenterT> callback) {
        Loader<PresenterT> loader = loaderManager.getLoader(loaderId);
        if (loader instanceof PresenterSupportLoader) {
            PresenterSupportLoader presenterLoader = (PresenterSupportLoader) loader;
            Class<? extends PresenterT> presenterClazz = factory.getTypeClazz();
            Object _presenter = presenterLoader.getPresenter();
            if (presenterClazz.isInstance(_presenter)) callback.onPresenterReady(presenterClazz.cast(_presenter));
            else throw new IllegalStateException("Unexpected loader type: " + _presenter);
        } else {
            loaderManager.initLoader(loaderId, savedState, new LoaderManager.LoaderCallbacks<PresenterT>() {
                @Override
                public Loader<PresenterT> onCreateLoader(int id, Bundle args) {
                    return new PresenterSupportLoader<>(context, factory, args);
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

    public interface Callback<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>> {
        void onPresenterReady(PresenterT presenter);

        void onPresenterDestroyed();
    }
}
