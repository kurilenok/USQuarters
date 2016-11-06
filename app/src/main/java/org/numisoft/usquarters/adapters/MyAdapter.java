package org.numisoft.usquarters.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.numisoft.usquarters.R;
import org.numisoft.usquarters.fragments.AllFragment;
import org.numisoft.usquarters.fragments.DMintFragment;
import org.numisoft.usquarters.fragments.SMintFragment;
import org.numisoft.usquarters.models.Coin;
import org.numisoft.usquarters.models.CoinDao;
import org.numisoft.usquarters.models.Theme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukolka on 14.08.16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    public List<Coin> coins = new ArrayList<>();
    Activity activity;
    Theme theme;

    public interface OnDataClickListener {
        void onDataClick(Coin coin, int position);
    }

    private OnDataClickListener mOnDataClickListener;


    public MyAdapter(Context context, Fragment fragment, Activity activity) {
        this.context = context;
        this.activity = activity;
        this.mOnDataClickListener = (OnDataClickListener) fragment;

        if (fragment instanceof AllFragment) {
            this.theme = ((AllFragment) fragment).getTheme();
            coins = new CoinDao(context).getCoinsByTheme(theme);
        } else if (fragment instanceof DMintFragment) {
            coins = new CoinDao(context).getCoinsByTheme(Theme.PARKS_D);
        } else if (fragment instanceof SMintFragment) {
            coins = new CoinDao(context).getCoinsByTheme(Theme.STATES_D);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {

        holder.ivCoin.setImageResource(coins.get(position).getImageId());
        holder.tvName.setText(coins.get(position).getName());
        holder.tvYear.setText(coins.get(position).getYear());
        holder.tvUNC.setText(Integer.toString(coins.get(position).getUnc()));

        if (coins.get(position).getName().equalsIgnoreCase("Gettysburg") ||
                coins.get(position).getName().equalsIgnoreCase("Homestead") ||
                coins.get(position).getName().equalsIgnoreCase("Adams")) {
            holder.rlHolder.setBackground(context.getDrawable(R.drawable.backgr));
        }


        holder.ivCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int currentUnc = coins.get(position).getUnc();
//                coins.get(position).setUnc(currentUnc + 1);
//                notifyItemChanged(position);
                mOnDataClickListener.onDataClick(coins.get(position), position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return coins.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivCoin;
        TextView tvName;
        TextView tvYear;
        TextView tvUNC;
        RelativeLayout rlHolder;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCoin = (ImageView) itemView.findViewById(R.id.ivCoin);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvYear = (TextView) itemView.findViewById(R.id.tvYear);
            tvUNC = (TextView) itemView.findViewById(R.id.tvUNC);
            rlHolder = (RelativeLayout) itemView.findViewById(R.id.rlHolder);
//            rlHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Coin coin = coins.get(getAdapterPosition());

//            FragmentManager manager = ((FragmentActivity) activity).getSupportFragmentManager();
//            PopupFragment popup = PopupFragment.getInstance(coin);
//            popup.show(manager, "1");

        }
    }
}
