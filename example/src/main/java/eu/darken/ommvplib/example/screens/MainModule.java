package eu.darken.ommvplib.example.screens;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private final MainActivity activity;

    public MainModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @MainScope
    public MainActivity provideActivity() {
        return activity;
    }

    @Provides
    @MainScope
    public MainPagerAdapter provideAdapter() {
        return new MainPagerAdapter(activity.getSupportFragmentManager(), activity);
    }

    @MainScope
    @Provides
    public int provideDefaultStartPage() {
        return 0;
    }

}
