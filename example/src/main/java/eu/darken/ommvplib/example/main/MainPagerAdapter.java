package eu.darken.ommvplib.example.main;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.example.pager.BasePagerFragment;
import eu.darken.ommvplib.example.pager.counting.CountingFragment;
import eu.darken.ommvplib.example.pager.shifting.ShiftingFragment;

class MainPagerAdapter extends FragmentPagerAdapter {

    private enum TabItem {
        COUNTING1(CountingFragment.class, R.string.tab_counting_1),
        SHIFTING1(ShiftingFragment.class, R.string.tab_shifting_1),
        COUNTING2(CountingFragment.class, R.string.tab_counting_2),
        SHIFTING2(ShiftingFragment.class, R.string.tab_shifting_2);

        private final Class<? extends BasePagerFragment<?, ?, ?>> fragmentClass;
        private final int titleResId;

        TabItem(Class<? extends BasePagerFragment<?, ?, ?>> fragmentClass, @StringRes int titleResId) {
            this.fragmentClass = fragmentClass;
            this.titleResId = titleResId;
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
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("fragment must have public no-arg constructor: " + fragmentClass.getName(), e);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(tabItems[position].titleResId);
    }

    @Override
    public int getCount() {
        return tabItems.length;
    }

}
