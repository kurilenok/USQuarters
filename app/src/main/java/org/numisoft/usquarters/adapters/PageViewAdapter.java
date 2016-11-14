package org.numisoft.usquarters.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.numisoft.usquarters.fragments.AllFragment;
import org.numisoft.usquarters.fragments.NeedFragment;
import org.numisoft.usquarters.fragments.NotUncFragment;
import org.numisoft.usquarters.fragments.SwapFragment;
import org.numisoft.usquarters.models.Theme;

/**
 * Created by kukolka on 22.08.16.
 */
public class PageViewAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;
    Theme theme;


    public PageViewAdapter(FragmentManager fm, int numberOfTabs, Theme theme) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.theme = theme;

//        Fragment fragment = getItem(0);
//        fm.beginTransaction().add(fragment, "all_fragment").commit();

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AllFragment(theme);
            case 1:
                return new NeedFragment(theme);
            case 2:
                return new SwapFragment(theme);
            case 3:
                return new NotUncFragment(theme);
            default:
                return new AllFragment(theme);
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
