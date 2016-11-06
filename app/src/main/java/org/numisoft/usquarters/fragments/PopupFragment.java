package org.numisoft.usquarters.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.numisoft.usquarters.R;
import org.numisoft.usquarters.models.Coin;
import org.numisoft.usquarters.models.CoinDao;

/**
 * Created by kukolka on 10/31/2016.
 */

public class PopupFragment extends DialogFragment implements View.OnClickListener {

    private Coin coin;

    private static PopupFragment instance = null;

    private PopupFragment() {}

    public static PopupFragment getInstance(Coin coin) {
        if (instance == null)
            instance = new PopupFragment();
        Bundle args = new Bundle();
        args.putSerializable("coin", coin);
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.popup, container);

        coin = (Coin) getArguments().getSerializable("coin");

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        tvName.setText(coin.getName());

        TextView tvYear = (TextView) view.findViewById(R.id.tvYear);
        tvYear.setText(coin.getYear());

        TextView tvUNC = (TextView) view.findViewById(R.id.tvUNC);
        tvUNC.setText(Integer.toString(coin.getUnc()));

        Button bAdd = (Button) view.findViewById(R.id.bAdd);
        bAdd.setOnClickListener(this);

        Button bDelete = (Button) view.findViewById(R.id.bDelete);
        bDelete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        CoinDao coinDao = new CoinDao(getActivity().getBaseContext());

        switch (view.getId()) {
            case R.id.bAdd:
                coinDao.addUnc(coin);
                break;
            case R.id.bDelete:
                coinDao.deleteUnc(coin);
                break;
        }

        AllFragment allFragment = (AllFragment) getTargetFragment();
        allFragment.onSomethingHappen();

    }


}
