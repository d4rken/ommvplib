package eu.darken.ommvplib.example.pager.counting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import eu.darken.ommvplib.injection.ComponentPresenter;


@CountingScope
public class CountingPresenter extends ComponentPresenter<CountingView, CountingComponent> {

    @Inject Counter counter;

    @Inject
    CountingPresenter() {
        // ;
    }

    void onCountClick() {
        getView().showText(String.valueOf(counter.countUp()));
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {

    }

    @Override
    public void onBind(@Nullable CountingView view) {
        super.onBind(view);
        if (view != null) view.showText(String.valueOf(counter.getCurrent()));
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }

    @Override
    public void onDestroy() {

    }
}
