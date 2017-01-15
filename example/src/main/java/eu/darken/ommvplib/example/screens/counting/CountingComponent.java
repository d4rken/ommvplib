package eu.darken.ommvplib.example.screens.counting;

import dagger.Subcomponent;
import eu.darken.ommvplib.injection.BaseComponent;

@CountingScope
@Subcomponent(modules = {CountingModule.class})
public interface CountingComponent extends BaseComponent<CountingView, CountingPresenter> {
}
