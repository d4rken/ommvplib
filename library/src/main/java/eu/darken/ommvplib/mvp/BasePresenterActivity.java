package eu.darken.ommvplib.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public abstract class BasePresenterActivity<
        ViewT extends BaseView,
        PresenterT extends BasePresenter<ViewT>>
        extends AppCompatActivity implements PresenterLoaderHelper.Callback<ViewT, PresenterT> {

    private static final int DEFAULT_LOADER_ID = 2017;

    protected PresenterT presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new PresenterLoaderHelper<ViewT, PresenterT>(this, getSupportLoaderManager(), getLoaderId())
                .fetchPresenter(getPresenterFactory(), savedInstanceState, new PresenterLoaderHelper.Callback<ViewT, PresenterT>() {
                    @Override
                    public void onPresenterReady(@NonNull PresenterT presenter) {
                        BasePresenterActivity.this.presenter = presenter;
                        BasePresenterActivity.this.onPresenterReady(presenter);
                    }

                    @Override
                    public void onPresenterDestroyed() {
                        BasePresenterActivity.this.presenter = null;
                        BasePresenterActivity.this.onPresenterDestroyed();
                    }
                });
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
        if (isFinishing()) {
            presenter.onDestroy();
        }
        super.onDestroy();
    }

    public int getLoaderId() {
        return DEFAULT_LOADER_ID;
    }

    @Override
    public void onPresenterReady(@NonNull PresenterT presenter) {

    }

    @Override
    public void onPresenterDestroyed() {

    }

    @NonNull
    protected abstract PresenterLoader.PresenterFactory<PresenterT> getPresenterFactory();

    public PresenterT getPresenter() {
        return presenter;
    }

    // Override in case of fragment not implementing Presenter<View> interface
    protected ViewT getPresenterView() {
        //noinspection unchecked
        return (ViewT) this;
    }
}
