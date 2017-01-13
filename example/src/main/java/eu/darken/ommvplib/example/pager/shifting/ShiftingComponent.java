package eu.darken.ommvplib.example.pager.shifting;

import dagger.Subcomponent;
import eu.darken.ommvplib.injection.BaseComponent;

@ShiftingScope
@Subcomponent(modules = {ShiftingModule.class})
public interface ShiftingComponent extends BaseComponent<ShiftingView, ShiftingPresenter> {
}
