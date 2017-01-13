package eu.darken.ommvplib.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;


public class PresenterLoaderHelper<ViewT extends BaseView, PresenterT extends BasePresenter<ViewT>> {
    private final int loaderId;
    private final LoaderManager loaderManager;
    final Context context;

    public PresenterLoaderHelper(@NonNull Context context, @NonNull LoaderManager manager, int loaderId) {
        this.context = context;
        this.loaderId = loaderId;
        this.loaderManager = manager;
    }

    public void fetchPresenter(
            @NonNull final PresenterLoader.PresenterFactory<PresenterT> factory,
            @Nullable Bundle savedState,
            @NonNull final Callback<ViewT, PresenterT> callback) {

        Loader<PresenterT> loader = loaderManager.getLoader(loaderId);

        if (loader instanceof PresenterLoader) {
            fetchPresenterFromLoader((PresenterLoader) loader, factory.getTypeClazz(), callback);
        } else {
            loaderManager.initLoader(loaderId, savedState, new LoaderManager.LoaderCallbacks<PresenterT>() {
                @Override
                public Loader<PresenterT> onCreateLoader(int id, Bundle args) {
                    return new PresenterLoader<>(context, factory, args);
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

    private void fetchPresenterFromLoader(
            @NonNull PresenterLoader loader,
            @NonNull Class<? extends PresenterT> presenterClazz,
            @NonNull Callback<ViewT, PresenterT> presenterListener) {
        PresenterT presenter = null;
        Object _presenter = loader.getPresenter();
        if (presenterClazz.isInstance(_presenter)) presenter = presenterClazz.cast(_presenter);
        if (presenter != null) presenterListener.onPresenterReady(presenter);
        else throw new IllegalStateException("Unexpected loader type: " + _presenter);
    }

    public interface Callback<ViewT extends BaseView, PresenterT extends BasePresenter<ViewT>> {
        void onPresenterReady(@NonNull PresenterT presenter);

        void onPresenterDestroyed();
    }
}
