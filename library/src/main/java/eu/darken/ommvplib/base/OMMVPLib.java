package eu.darken.ommvplib.base;

import android.app.Activity;
import android.app.Fragment;
import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import eu.darken.ommvplib.base.support.PresenterSupportLoaderFactory;

public class OMMVPLib<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>> {
    private final LoaderFactory<ViewT, PresenterT> loaderFactory;
    private final LoaderFactory.Callback<ViewT, PresenterT> callback;
    private final InstanceStatePublisher statePublisher;
    private final LifecycleOwner lifecycleOwner;
    private PresenterT presenter;
    private Bundle savedState;

    OMMVPLib(Builder<ViewT, PresenterT> builder) {
        this.lifecycleOwner = builder.lifecycleOwner;
        this.callback = builder.callback;
        this.loaderFactory = builder.loaderFactory;
        this.statePublisher = builder.statePublisher;

        attach();
    }

    private void attach() {
        if (this.statePublisher != null) {
            this.statePublisher.setInternalCallback(new InstanceStateCallback() {
                @Override
                public void onCreate(@Nullable Bundle savedInstanceState) {
                    savedState = savedInstanceState;
                }

                @Override
                public void onSaveInstanceState(Bundle outState) {
                    if (presenter != null) presenter.onSaveInstanceState(outState);
                }
            });
        }

        this.lifecycleOwner.getLifecycle().addObserver(new DefaultLifecycleObserver() {
            @Override
            public void onCreate(@NonNull LifecycleOwner owner) {
                loaderFactory.load(savedState, new PresenterSupportLoaderFactory.Callback<ViewT, PresenterT>() {
                    @Override
                    public void onPresenterReady(PresenterT presenter) {
                        OMMVPLib.this.presenter = presenter;
                        callback.onPresenterReady(presenter);
                    }

                    @Override
                    public void onPresenterDestroyed() {
                        OMMVPLib.this.presenter = null;
                        callback.onPresenterDestroyed();
                    }
                });
            }

            @Override
            public void onResume(@NonNull LifecycleOwner owner) {
                //noinspection unchecked
                presenter.onBindChange((ViewT) owner);
            }

            @Override
            public void onPause(@NonNull LifecycleOwner owner) {
                presenter.onBindChange(null);
            }
        });
    }

    @Nullable
    public PresenterT getPresenter() {
        return presenter;
    }

    public static <ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>> Builder<ViewT, PresenterT> builder() {
        return new Builder<>();
    }

    public static class Builder<ViewT extends Presenter.View & LifecycleOwner, PresenterT extends Presenter<ViewT>> {
        private int loaderId = 2017;
        private PresenterSource<PresenterT> presenterSource;
        private LoaderFactory.Callback<ViewT, PresenterT> callback;
        private LifecycleOwner lifecycleOwner;
        private LoaderFactory<ViewT, PresenterT> loaderFactory;
        private InstanceStatePublisher statePublisher;

        /**
         * If you want the presenter to be able to store data via {@link Activity#onSaveInstanceState(Bundle)} then you need to call this.
         *
         * @param statePublisher pass a {@link InstanceStatePublisher object} that you have to call on in onCreate()/onSaveInstance()
         */
        public Builder<ViewT, PresenterT> statePublisher(InstanceStatePublisher statePublisher) {
            this.statePublisher = statePublisher;
            return this;
        }

        /**
         * For injection you probably want to pass a {@link eu.darken.ommvplib.injection.PresenterInjectionCallback}
         */
        public Builder<ViewT, PresenterT> presenterCallback(LoaderFactory.Callback<ViewT, PresenterT> callback) {
            this.callback = callback;
            return this;
        }

        /**
         * For injection pass an {@link eu.darken.ommvplib.injection.InjectedPresenter}
         */
        public Builder<ViewT, PresenterT> presenterSource(PresenterSource<PresenterT> presenterSource) {
            this.presenterSource = presenterSource;
            return this;
        }

        /**
         * @param lifecycleOwner Your {@link AppCompatActivity}, {@link Fragment} or {@link android.support.v4.app.Fragment}
         */
        public OMMVPLib<ViewT, PresenterT> attach(ViewT lifecycleOwner) {
            if (presenterSource == null) {
                if (lifecycleOwner instanceof PresenterSource) {
                    //noinspection unchecked
                    presenterSource = (PresenterSource<PresenterT>) lifecycleOwner;
                } else throw new NullPointerException("PresenterFactory is null");
            }

            if (loaderFactory == null) {
                if (lifecycleOwner instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) lifecycleOwner;
                    loaderFactory = new PresenterSupportLoaderFactory<>(activity, activity.getSupportLoaderManager(), loaderId, presenterSource);
                } else if (lifecycleOwner instanceof Activity) {
                    Activity activity = (Activity) lifecycleOwner;
                    loaderFactory = new PresenterLoaderFactory<>(activity, activity.getLoaderManager(), loaderId, presenterSource);
                } else if (lifecycleOwner instanceof Fragment) {
                    Fragment fragment = (Fragment) lifecycleOwner;
                    loaderFactory = new PresenterLoaderFactory<>(fragment.getActivity(), fragment.getLoaderManager(), loaderId, presenterSource);
                } else if (lifecycleOwner instanceof android.support.v4.app.Fragment) {
                    android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) lifecycleOwner;
                    loaderFactory = new PresenterSupportLoaderFactory<>(fragment.getContext(), fragment.getLoaderManager(), loaderId, presenterSource);
                } else {
                    throw new RuntimeException("Couldn't determine correct LoaderFactory.");
                }
            }
            this.lifecycleOwner = lifecycleOwner;
            return new OMMVPLib<>(this);
        }
    }

}