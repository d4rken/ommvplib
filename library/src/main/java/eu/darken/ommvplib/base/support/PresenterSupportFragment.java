package eu.darken.ommvplib.base.support;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterFactory;

public abstract class PresenterSupportFragment<
        ViewT extends Presenter.View,
        PresenterT extends Presenter<ViewT>>
        extends Fragment
        implements PresenterSupportLoaderHelper.Callback<ViewT, PresenterT> {

    private static final int DEFAULT_LOADER_ID = 2017;
    protected PresenterT presenter;

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new PresenterSupportLoaderHelper<ViewT, PresenterT>(getContext(), getLoaderManager(), savedInstanceState)
                .fetch(getPresenterFactory(), getLoaderId(), new PresenterSupportLoaderHelper.Callback<ViewT, PresenterT>() {
                    @Override
                    public void onPresenterReady(@NonNull PresenterT presenter) {
                        PresenterSupportFragment.this.presenter = presenter;
                        PresenterSupportFragment.this.onPresenterReady(presenter);
                    }

                    @Override
                    public void onPresenterDestroyed() {
                        PresenterSupportFragment.this.presenter = null;
                        PresenterSupportFragment.this.onPresenterDestroyed();
                    }
                });
    }

    public int getLoaderId() {
        return DEFAULT_LOADER_ID;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onBindChange(getPresenterView());
    }

    @Override
    public void onPause() {
        presenter.onBindChange(null);
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // may be called for detached Fragments.
        if (presenter != null) presenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPresenterReady(@NonNull PresenterT presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onPresenterDestroyed() { }

    @NonNull
    protected abstract PresenterFactory<PresenterT> getPresenterFactory();

    public PresenterT getPresenter() {
        return presenter;
    }

    protected ViewT getPresenterView() {
        //noinspection unchecked
        return (ViewT) this;
    }
}
