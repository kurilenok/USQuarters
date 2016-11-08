package org.numisoft.usquarters.fragments;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.numisoft.usquarters.R;
import org.numisoft.usquarters.models.Coin;
import org.numisoft.usquarters.models.CoinDao;

/**
 * Created by kukolka on 10/31/2016.
 */

public class PopupFragment extends DialogFragment implements View.OnClickListener {

    private Coin coin;
    TextView tvUNC, tvProof, tvFine, tvGood;
    Button bDeleteProof, bDeleteUnc, bDeleteFine, bDeleteGood;

    private static PopupFragment instance = null;

    private PopupFragment() {
    }

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

        coin = (Coin) getArguments().getSerializable("coin");

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));

        View view = inflater.inflate(R.layout.popup, container);

        CardView cv = (CardView) view.findViewById(R.id.cvItem);
        cv.setClipToOutline(true);

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvYear = (TextView) view.findViewById(R.id.tvYear);
        ImageView ivCoin = (ImageView) view.findViewById(R.id.ivCoin);

        tvUNC = (TextView) view.findViewById(R.id.tvUNC);
        tvProof = (TextView) view.findViewById(R.id.tvProof);
        tvFine = (TextView) view.findViewById(R.id.tvFine);
        tvGood = (TextView) view.findViewById(R.id.tvGood);

        Button bAddProof = (Button) view.findViewById(R.id.bAddProof);
        Button bAddUnc = (Button) view.findViewById(R.id.bAddUnc);
        Button bAddFine = (Button) view.findViewById(R.id.bAddFine);
        Button bAddGood = (Button) view.findViewById(R.id.bAddGood);


        bDeleteProof = (Button) view.findViewById(R.id.bDeleteProof);
        bDeleteUnc = (Button) view.findViewById(R.id.bDeleteUnc);
        bDeleteFine = (Button) view.findViewById(R.id.bDeleteFine);
        bDeleteGood = (Button) view.findViewById(R.id.bDeleteGood);


        Button bUncClose = (Button) view.findViewById(R.id.bUncClose);
        bUncClose.setOnClickListener(this);

        tvName.setText(coin.getName());
        tvYear.setText(coin.getYear());
        ivCoin.setImageResource(getResources().getIdentifier(coin.getImageId(), "drawable", getContext().getPackageName()));

        tvProof.setText(Integer.toString(coin.getProof()));
        bAddProof.setOnClickListener(this);
        bDeleteProof.setOnClickListener(this);
        if (coin.getProof() == 0) bDeleteProof.setEnabled(false);

        tvUNC.setText(Integer.toString(coin.getUnc()));
        bAddUnc.setOnClickListener(this);
        bDeleteUnc.setOnClickListener(this);
        if (coin.getUnc() == 0) bDeleteUnc.setEnabled(false);

        tvFine.setText(Integer.toString(coin.getFine()));
        bAddFine.setOnClickListener(this);
        bDeleteFine.setOnClickListener(this);
        if (coin.getFine() == 0) bDeleteFine.setEnabled(false);

        tvGood.setText(Integer.toString(coin.getGood()));
        bAddGood.setOnClickListener(this);
        bDeleteGood.setOnClickListener(this);
        if (coin.getGood() == 0) bDeleteGood.setEnabled(false);


        return view;
    }

    @Override
    public void onClick(View view) {

        CoinDao coinDao = new CoinDao(getActivity().getBaseContext());
        AllFragment allFragment = (AllFragment) getTargetFragment();
        int currentProof = coin.getProof();
        int currentUnc = coin.getUnc();
        int currentFine = coin.getFine();
        int currentGood = coin.getGood();

        switch (view.getId()) {
            case R.id.bUncClose:
                coin.setUnc(++currentUnc);
                this.dismiss();
                break;
            case R.id.bAddProof:
                coin.setProof(++currentProof);
                bDeleteProof.setEnabled(true);
                break;
            case R.id.bDeleteProof:
                coin.setProof(--currentProof);
                if (coin.getProof() == 0) bDeleteProof.setEnabled(false);
                break;
            case R.id.bAddUnc:
                coin.setUnc(++currentUnc);
                bDeleteUnc.setEnabled(true);
                break;
            case R.id.bDeleteUnc:
                coin.setUnc(--currentUnc);
                if (coin.getUnc() == 0) bDeleteUnc.setEnabled(false);
                break;
            case R.id.bAddFine:
                coin.setFine(++currentFine);
                bDeleteFine.setEnabled(true);
                break;
            case R.id.bDeleteFine:
                coin.setFine(--currentFine);
                if (coin.getFine() == 0) bDeleteFine.setEnabled(false);
                break;
            case R.id.bAddGood:
                coin.setGood(++currentGood);
                bDeleteGood.setEnabled(true);
                break;
            case R.id.bDeleteGood:
                coin.setGood(--currentGood);
                if (coin.getGood() == 0) bDeleteGood.setEnabled(false);
                break;
        }

        tvUNC.setText(Integer.toString(coin.getUnc()));
        tvProof.setText(Integer.toString(coin.getProof()));
        tvFine.setText(Integer.toString(coin.getFine()));
        tvGood.setText(Integer.toString(coin.getGood()));
        coinDao.updateCoin(coin);
        allFragment.doSomething(coin);
    }

}
