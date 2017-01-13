package eu.darken.ommvplib.example.pager.shifting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import eu.darken.ommvplib.injection.ComponentPresenter;

@ShiftingScope
public class ShiftingPresenter extends ComponentPresenter<ShiftingView, ShiftingComponent> {

    @Inject Shifter shifter;

    @Inject
    ShiftingPresenter() {
        // ;
    }

    void onShiftClick() {
        getView().showText(shifter.shift());
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {

    }

    @Override
    public void onBind(@Nullable ShiftingView view) {
        super.onBind(view);
        if (view != null) view.showText(shifter.getCurrent());
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

    @Override
    public void onDestroy() {

    }
}
