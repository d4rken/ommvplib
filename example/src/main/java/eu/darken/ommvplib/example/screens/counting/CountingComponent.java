package eu.darken.ommvplib.example.screens.counting;

import dagger.Subcomponent;
import eu.darken.ommvplib.injection.PresenterComponent;
import eu.darken.ommvplib.injection.fragment.FragmentComponent;
import eu.darken.ommvplib.injection.fragment.FragmentComponentBuilder;

@CountingScope
@Subcomponent(modules = {CountingModule.class})
public interface CountingComponent extends FragmentComponent<CountingFragment>, PresenterComponent<CountingView, CountingPresenter> {

    @Subcomponent.Builder
    interface Builder extends FragmentComponentBuilder<CountingFragment, CountingComponent> {
        CountingComponent.Builder module(CountingModule module);
    }
}
