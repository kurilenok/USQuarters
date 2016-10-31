package org.numisoft.usquarters.adapters;


import android.app.Activity;

import android.app.FragmentManager;
import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.numisoft.usquarters.R;
import org.numisoft.usquarters.fragments.AllFragment;
import org.numisoft.usquarters.fragments.DMintFragment;
import org.numisoft.usquarters.fragments.PopupFragment;
import org.numisoft.usquarters.fragments.SMintFragment;
import org.numisoft.usquarters.models.Coin;
import org.numisoft.usquarters.models.CoinDAO;
import org.numisoft.usquarters.models.Mint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukolka on 14.08.16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    List<Coin> coins = new ArrayList<>();
    Activity activity;

    public MyAdapter(Context context, Fragment fragment, Activity activity) {
        this.context = context;
        this.activity = activity;

        if (fragment instanceof AllFragment) {
            coins = new CoinDAO(context).getCoins();
        } else if (fragment instanceof DMintFragment) {
            coins = new CoinDAO(context).getCoinsByMint(Mint.D);
        } else if (fragment instanceof SMintFragment) {
            coins = new CoinDAO(context).getCoinsByMint(Mint.S);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        holder.ivCoin.setImageResource(coins.get(position).getImageId());
        holder.tvName.setText(coins.get(position).getName());
        holder.tvYear.setText(coins.get(position).getYear());

        if (coins.get(position).getName().equalsIgnoreCase("Gettysburg") ||
                coins.get(position).getName().equalsIgnoreCase("Homestead") ||
                coins.get(position).getName().equalsIgnoreCase("Adams")) {
            holder.rlHolder.setBackground(context.getDrawable(R.drawable.backgr));
        }



    }



    @Override
    public int getItemCount() {
        return coins.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivCoin;
        TextView tvName;
        TextView tvYear;
        RelativeLayout rlHolder;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCoin = (ImageView) itemView.findViewById(R.id.ivCoin);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvYear = (TextView) itemView.findViewById(R.id.tvYear);
            rlHolder = (RelativeLayout) itemView.findViewById(R.id.rlHolder);
            rlHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            String select = coins.get(getAdapterPosition()).getName();

            FragmentManager manager = activity.getFragmentManager();
            PopupFragment popup = PopupFragment.getInstance(select);
            popup.show(manager, "1");

        }
    }
}
