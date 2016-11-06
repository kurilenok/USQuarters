package org.numisoft.usquarters.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.numisoft.usquarters.R;
import org.numisoft.usquarters.adapters.MyAdapter;
import org.numisoft.usquarters.models.Coin;
import org.numisoft.usquarters.models.Theme;

/**
 * Created by kukolka on 22.08.16.
 */
public class AllFragment extends Fragment implements MyAdapter.OnDataClickListener {

    Theme theme;
    View view;
    RecyclerView rvMain;
    MyAdapter myAdapter;
    int clicked;

    public AllFragment(Theme theme) {
        this.theme = theme;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.all_fragment, container, false);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(view.getContext(), (int) (dpWidth / 160));

        rvMain = (RecyclerView) view.findViewById(R.id.rvMain);
        rvMain.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(view.getContext(), this, getActivity());

        rvMain.setAdapter(myAdapter);
        return view;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void onSomethingHappen() {
        int currentUnc = myAdapter.coins.get(clicked).getUnc();
        myAdapter.coins.get(clicked).setUnc(currentUnc + 1);
        myAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDataClick(Coin coin, int position) {
        this.clicked = position;
        FragmentManager manager = getActivity().getSupportFragmentManager();
        PopupFragment popup = PopupFragment.getInstance(coin);
        popup.setTargetFragment(this, 0);
        popup.show(manager, "1");
    }
}
