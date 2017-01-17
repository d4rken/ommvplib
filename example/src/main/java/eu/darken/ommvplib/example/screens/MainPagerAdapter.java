package eu.darken.ommvplib.example.screens;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

class MainPagerAdapter extends FragmentPagerAdapter {

    private final List<FragmentObj> fragments;

    static class FragmentObj {
        final Class<? extends Fragment> fragmentClass;
        final String title;

        FragmentObj(Class<? extends Fragment> fragmentClass, String title) {
            this.fragmentClass = fragmentClass;
            this.title = title;
        }
    }

    MainPagerAdapter(FragmentManager fragmentManager, List<FragmentObj> fragments) {
        super(fragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return newInstance(fragments.get(position).fragmentClass);
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
        return fragments.get(position).title;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
