package org.numisoft.usquarters.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.numisoft.usquarters.fragments.AllFragment;
import org.numisoft.usquarters.fragments.DMintFragment;
import org.numisoft.usquarters.fragments.SMintFragment;
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
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AllFragment(theme);
        }
        else if (position == 1) {
            return new DMintFragment();
        }
        else {
            return new SMintFragment();
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
