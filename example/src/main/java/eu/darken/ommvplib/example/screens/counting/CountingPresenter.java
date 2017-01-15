package eu.darken.ommvplib.example.screens.counting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import eu.darken.ommvplib.injection.ComponentPresenter;
import timber.log.Timber;


@CountingScope
public class CountingPresenter extends ComponentPresenter<CountingView, CountingComponent> {

    @Inject Counter counter;

    @Inject
    CountingPresenter() {
        Timber.d("Instantiated.");
    }

    void onCountClick() {
        if (getView() != null) getView().showText(String.valueOf(counter.countUp()));
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        Timber.d("onCreate(%s)", bundle);
        if (bundle != null) counter.setCounter(bundle.getInt("counter"));
    }

    @Override
    public void onBindChange(@Nullable CountingView view) {
        super.onBindChange(view);
        Timber.d("onViewChange(%s)", view);
        if (view != null) view.showText(String.valueOf(counter.getCurrent()));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        Timber.d("onSaveInstanceState(%s)", bundle);
        bundle.putInt("counter", counter.getCurrent());
    }

    @Override
    public void onDestroy() {
        Timber.d("onDestroy()");
    }
}
