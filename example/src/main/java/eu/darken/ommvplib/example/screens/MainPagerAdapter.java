package eu.darken.ommvplib.example.screens;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import eu.darken.ommvplib.example.screens.counting.CountingFragment;

class MainPagerAdapter extends FragmentPagerAdapter {

    private enum TabItem {
        COUNTING1(CountingFragment.class, "Tab 1"),
        COUNTING2(CountingFragment.class, "Tab 2"),
        COUNTING3(CountingFragment.class, "Tab 3"),
        COUNTING4(CountingFragment.class, "Tab 4"),
        COUNTING5(CountingFragment.class, "Tab 5");

        private final Class<? extends BasePagerFragment<?, ?, ?>> fragmentClass;
        private final String title;

        TabItem(Class<? extends BasePagerFragment<?, ?, ?>> fragmentClass, String title) {
            this.fragmentClass = fragmentClass;
            this.title = title;
        }
    }

    private final TabItem[] tabItems = TabItem.values();
    private final Context context;

    MainPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return newInstance(tabItems[position].fragmentClass);
    }

    private Fragment newInstance(Class<? extends Fragment> fragmentClass) {
        try {
            return fragmentClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabItems[position].title;
    }

    @Override
    public int getCount() {
        return tabItems.length;
    }

}
