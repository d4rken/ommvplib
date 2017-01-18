package eu.darken.ommvplib.injection;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterFactory;
import eu.darken.ommvplib.base.PresenterFragment;
import eu.darken.ommvplib.injection.fragment.FragmentComponent;
import eu.darken.ommvplib.injection.fragment.FragmentComponentBuilder;
import eu.darken.ommvplib.injection.fragment.FragmentComponentBuilderSource;

public abstract class ComponentPresenterFragment<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends PresenterComponent<ViewT, PresenterT>>
        extends PresenterFragment<ViewT, PresenterT>
        implements PresenterFactory<PresenterT> {

    @NonNull
    @Override
    protected PresenterFactory<PresenterT> getPresenterFactory() {
        return this;
    }

    @Override
    public PresenterT create() {
        final ComponentT component = createComponent();
        final PresenterT presenter = component.getPresenter();
        presenter.component = component;
        return presenter;
    }

    @Override
    public void onPresenterReady(@NonNull PresenterT presenter) {
        super.onPresenterReady(presenter);
        this.presenter = presenter;
        inject(presenter.component);
    }

    public void inject(@NonNull ComponentT component) {
    }

    public abstract ComponentT createComponent();

    public static <A extends Fragment, B extends FragmentComponentBuilder<A, ? extends FragmentComponent<A>>> B getComponentBuilder(A fragment) {
        final FragmentComponentBuilderSource source = (FragmentComponentBuilderSource) fragment.getActivity();
        //noinspection unchecked
        final FragmentComponentBuilder<A, ? extends FragmentComponent<A>> builder = source.getComponentBuilder((Class<A>) fragment.getClass());
        //noinspection unchecked
        return (B) builder;
    }
}
