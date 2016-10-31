package org.numisoft.usquarters.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.numisoft.usquarters.adapters.MyAdapter;
import org.numisoft.usquarters.R;

/**
 * Created by kukolka on 22.08.16.
 */
public class NewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_fragment, container, false);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(view.getContext(), (int) (dpWidth / 160));

        RecyclerView rvMain = (RecyclerView) view.findViewById(R.id.rvMain);
        rvMain.setLayoutManager(layoutManager);
        rvMain.setAdapter(new MyAdapter(view.getContext(), this, getActivity()));

        return view;
    }
}
