package eu.darken.ommvplib.example.screens.counting;

import dagger.Subcomponent;
import eu.darken.ommvplib.injection.PresenterComponent;
import eu.darken.ommvplib.injection.fragment.support.SupportFragmentComponent;

@CountingScope
@Subcomponent(modules = {CountingModule.class})
public interface CountingComponent extends SupportFragmentComponent<CountingFragment>, PresenterComponent<CountingView, CountingPresenter> {

    @Subcomponent.Builder
    abstract class Builder extends SupportFragmentComponent.Builder<CountingFragment, CountingComponent> {
        public abstract Builder module(CountingModule module);
    }
}
