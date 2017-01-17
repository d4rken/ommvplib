package eu.darken.ommvplib.example.screens;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import eu.darken.ommvplib.example.screens.counting.CountingFragment;

@Module
public class MainModule {
    private final MainActivity activity;

    MainModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @MainScope
    MainActivity provideActivity() {
        return activity;
    }

    @Provides
    @MainScope
    MainPagerAdapter provideAdapter() {
        List<MainPagerAdapter.FragmentObj> fragments = new ArrayList<>();
        fragments.add(new MainPagerAdapter.FragmentObj(CountingFragment.class, "1"));
        fragments.add(new MainPagerAdapter.FragmentObj(CountingFragment.class, "2"));
        fragments.add(new MainPagerAdapter.FragmentObj(CountingFragment.class, "3"));
        fragments.add(new MainPagerAdapter.FragmentObj(CountingFragment.class, "4"));
        fragments.add(new MainPagerAdapter.FragmentObj(CountingFragment.class, "5"));
        return new MainPagerAdapter(activity.getSupportFragmentManager(), fragments);
    }

    @MainScope
    @Provides
    int provideDefaultStartPage() {
        return 0;
    }

}
