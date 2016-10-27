package org.numisoft.usquarters.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.numisoft.usquarters.fragments.AllFragment;
import org.numisoft.usquarters.fragments.DMintFragment;
import org.numisoft.usquarters.fragments.SMintFragment;

/**
 * Created by kukolka on 22.08.16.
 */
public class PageViewAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public PageViewAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;

    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AllFragment();
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
}
