package eu.darken.ommvplib.example.screens.counting;

import dagger.Subcomponent;
import eu.darken.ommvplib.injection.PresenterComponent;

@CountingScope
@Subcomponent(modules = {CountingModule.class})
public interface CountingComponent extends PresenterComponent<CountingView, CountingPresenter> {
}
