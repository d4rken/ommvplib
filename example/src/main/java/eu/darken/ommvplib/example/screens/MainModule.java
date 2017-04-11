package eu.darken.ommvplib.example.screens;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import eu.darken.ommvplib.example.screens.counting.CountingFragment;
import eu.darken.ommvplib.example.screens.debug.DebugFragment;

@Module
public class MainModule {

    @Provides
    @MainScope
    List<MainPagerAdapter.FragmentObj> provideAdapter() {
        List<MainPagerAdapter.FragmentObj> fragments = new ArrayList<>();
        fragments.add(new MainPagerAdapter.FragmentObj(CountingFragment.class, "1"));
        fragments.add(new MainPagerAdapter.FragmentObj(CountingFragment.class, "2"));
        fragments.add(new MainPagerAdapter.FragmentObj(CountingFragment.class, "3"));
        fragments.add(new MainPagerAdapter.FragmentObj(CountingFragment.class, "4"));
        fragments.add(new MainPagerAdapter.FragmentObj(CountingFragment.class, "5"));
        fragments.add(new MainPagerAdapter.FragmentObj(DebugFragment.class, "Debug"));
        return fragments;
    }

    @MainScope
    @Provides
    int provideDefaultStartPage() {
        return 0;
    }

}
