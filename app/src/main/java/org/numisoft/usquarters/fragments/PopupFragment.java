package org.numisoft.usquarters.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.numisoft.usquarters.R;

/**
 * Created by kukolka on 10/31/2016.
 */

public class PopupFragment extends DialogFragment {

    private static PopupFragment instance = null;

    private PopupFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.popup, container);
        TextView tvPopup = (TextView) view.findViewById(R.id.tvPopup);
        tvPopup.setText(getArguments().getString("select"));

        return view;
    }

    public static PopupFragment getInstance(String select) {
        if (instance == null)
            instance = new PopupFragment();
        Bundle args = new Bundle();
        args.putString("select", select);
        instance.setArguments(args);

        return instance;
    }

}
