package eu.darken.ommvplib.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class PresenterFragment<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>>
        extends Fragment
        implements PresenterLoaderHelper.Callback<ViewT, PresenterT> {

    private static final int DEFAULT_LOADER_ID = 2017;
    protected PresenterT presenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new PresenterLoaderHelper<ViewT, PresenterT>(getActivity(), getLoaderManager(), savedInstanceState)
                .fetch(getPresenterFactory(), getLoaderId(), new PresenterLoaderHelper.Callback<ViewT, PresenterT>() {
                    @Override
                    public void onPresenterReady(PresenterT presenter) {
                        PresenterFragment.this.presenter = presenter;
                        PresenterFragment.this.onPresenterReady(presenter);
                    }

                    @Override
                    public void onPresenterDestroyed() {
                        PresenterFragment.this.presenter = null;
                        PresenterFragment.this.onPresenterDestroyed();
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
    public void onPresenterReady(PresenterT presenter) {
        this.presenter = presenter;
    }

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
