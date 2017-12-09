package eu.darken.ommvplib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;


public abstract class LoaderFactory<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>> {
    private final PresenterSource<PresenterT> factory;
    private final int loaderId;
    private final Context context;

    protected LoaderFactory(Context context, int loaderId, PresenterSource<PresenterT> factory) {
        this.context = context;
        this.loaderId = loaderId;
        this.factory = factory;
    }

    public Context getContext() {
        return context;
    }

    public int getLoaderId() {
        return loaderId;
    }

    public PresenterSource<PresenterT> getFactory() {
        return factory;
    }

    public abstract void load(@Nullable Bundle savedState, final Callback<ViewT, PresenterT> callback);

    public interface Callback<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>> {
        void onPresenterReady(PresenterT presenter);

        void onPresenterDestroyed();
    }
}
