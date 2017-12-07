package eu.darken.ommvplib.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import eu.darken.ommvplib.base.support.PresenterSupportLoaderHelper;

public abstract class PresenterPreferenceFragment<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>>
        extends PreferenceFragmentCompat
        implements PresenterSupportLoaderHelper.Callback<ViewT, PresenterT> {

    private static final int DEFAULT_LOADER_ID = 2017;
    protected PresenterT presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new PresenterSupportLoaderHelper<ViewT, PresenterT>(getContext(), getLoaderManager(), savedInstanceState)
                .fetch(getPresenterFactory(), getLoaderId(), new PresenterSupportLoaderHelper.Callback<ViewT, PresenterT>() {
                    @Override
                    public void onPresenterReady(PresenterT presenter) {
                        PresenterPreferenceFragment.this.presenter = presenter;
                        PresenterPreferenceFragment.this.onPresenterReady(presenter);
                    }

                    @Override
                    public void onPresenterDestroyed() {
                        PresenterPreferenceFragment.this.presenter = null;
                        PresenterPreferenceFragment.this.onPresenterDestroyed();
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
    public void onPresenterReady(PresenterT presenter) { }

    @Override
    public void onPresenterDestroyed() { }

    protected abstract PresenterFactory<PresenterT> getPresenterFactory();

    public PresenterT getPresenter() {
        return presenter;
    }

    protected ViewT getPresenterView() {
        //noinspection unchecked
        return (ViewT) this;
    }
}
