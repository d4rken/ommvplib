package eu.darken.ommvplib.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class BasePresenterFragment<
        ViewT extends BaseView,
        PresenterT extends BasePresenter<ViewT>>
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
        new PresenterLoaderHelper<ViewT, PresenterT>(getContext(), getLoaderManager(), getLoaderId())
                .fetchPresenter(getPresenterFactory(), savedInstanceState, new PresenterLoaderHelper.Callback<ViewT, PresenterT>() {
                    @Override
                    public void onPresenterReady(@NonNull PresenterT presenter) {
                        BasePresenterFragment.this.presenter = presenter;
                        BasePresenterFragment.this.onPresenterReady(presenter);
                    }

                    @Override
                    public void onPresenterDestroyed() {
                        BasePresenterFragment.this.presenter = null;
                        BasePresenterFragment.this.onPresenterDestroyed();
                    }
                });
    }

    public int getLoaderId() {
        return DEFAULT_LOADER_ID;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onBind(getPresenterView());
    }

    @Override
    public void onPause() {
        presenter.onBind(null);
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        presenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        final Activity activity = getActivity();
        if (activity != null && activity.isFinishing()) presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPresenterReady(@NonNull PresenterT presenter) { }

    @Override
    public void onPresenterDestroyed() { }

    @NonNull
    protected abstract PresenterLoader.PresenterFactory<PresenterT> getPresenterFactory();

    public PresenterT getPresenter() {
        return presenter;
    }

    protected ViewT getPresenterView() {
        //noinspection unchecked
        return (ViewT) this;
    }
}
