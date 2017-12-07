package eu.darken.ommvplib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import eu.darken.ommvplib.base.support.PresenterSupportLoaderHelper;


public abstract class PresenterActivity<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>>
        extends AppCompatActivity
        implements PresenterSupportLoaderHelper.Callback<ViewT, PresenterT> {

    private static final int DEFAULT_LOADER_ID = 2017;
    protected PresenterT presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new PresenterSupportLoaderHelper<ViewT, PresenterT>(this, getSupportLoaderManager(), savedInstanceState)
                .fetch(getPresenterFactory(), getLoaderId(), new PresenterSupportLoaderHelper.Callback<ViewT, PresenterT>() {
                    @Override
                    public void onPresenterReady(PresenterT presenter) {
                        PresenterActivity.this.presenter = presenter;
                        PresenterActivity.this.onPresenterReady(presenter);
                    }

                    @Override
                    public void onPresenterDestroyed() {
                        PresenterActivity.this.presenter = null;
                        PresenterActivity.this.onPresenterDestroyed();
                    }
                });
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
        presenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public int getLoaderId() {
        return DEFAULT_LOADER_ID;
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
