package org.numisoft.usquarters.adapters;


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
import org.numisoft.usquarters.fragments.BasicFragment;
import org.numisoft.usquarters.fragments.NeedFragment;
import org.numisoft.usquarters.fragments.NotUncFragment;
import org.numisoft.usquarters.fragments.SwapFragment;
import org.numisoft.usquarters.models.Coin;
import org.numisoft.usquarters.models.CoinDao;
import org.numisoft.usquarters.models.Theme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukolka on 14.08.16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Coin> coins = new ArrayList<>();
    private Theme theme;


    public interface OnDataClickListener {
        void onDataClick(Coin coin, int position);
    }

    private OnDataClickListener mOnDataClickListener;


    public MyAdapter(Context context, Fragment fragment) {
        this.context = context;
        this.mOnDataClickListener = (OnDataClickListener) fragment;
        theme = ((BasicFragment) fragment).getTheme() == null ?
                Theme.PRESIDENTS_P : ((BasicFragment) fragment).getTheme();

        if (fragment instanceof AllFragment) {
            coins = new CoinDao(context).getAllCoins(theme);
        } else if (fragment instanceof NeedFragment) {
            coins = new CoinDao(context).getNeedCoins(theme);
        } else if (fragment instanceof SwapFragment) {
            coins = new CoinDao(context).getSwapCoins(theme);
        } else if (fragment instanceof NotUncFragment) {
            coins = new CoinDao(context).getNotUncCoins(theme);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {

        int imageResource = context.getResources().getIdentifier(
                coins.get(position).getImageId(), "drawable", context.getPackageName());

        holder.ivCoin.setImageResource(imageResource);
        holder.tvName.setText(coins.get(position).getName());
        holder.tvYear.setText(coins.get(position).getYear());
        holder.tvMark.setText(coins.get(position).getMark());
        holder.tvProof.setText("Proof: " + Integer.toString(coins.get(position).getProof()));
        holder.tvUNC.setText(Integer.toString(coins.get(position).getUnc()));
        holder.tvFine.setText("F: " + Integer.toString(coins.get(position).getFine()));
        holder.tvGood.setText("G: " + Integer.toString(coins.get(position).getGood()));

        if (coins.get(position).getUnc() + coins.get(position).getProof()
                + coins.get(position).getFine() + coins.get(position).getGood() > 0)
            holder.rlHolder.setBackground(context.getDrawable(R.drawable.background));
        else holder.rlHolder.setBackground(context.getDrawable(R.drawable.background_zero));

        if (coins.get(position).getProof() == 0) holder.tvProof.setTextSize(0);
        else holder.tvProof.setTextSize(10);

        if (coins.get(position).getUnc() == 0) holder.tvUNC.setTextSize(0);
        else holder.tvUNC.setTextSize(14);

        if (coins.get(position).getFine() == 0) holder.tvFine.setTextSize(0);
        else holder.tvFine.setTextSize(10);

        if (coins.get(position).getGood() == 0) holder.tvGood.setTextSize(0);
        else holder.tvGood.setTextSize(10);

        holder.ivCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        TextView tvMark;
        TextView tvUNC, tvProof, tvFine, tvGood;
        RelativeLayout rlHolder;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCoin = (ImageView) itemView.findViewById(R.id.ivCoin);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvYear = (TextView) itemView.findViewById(R.id.tvYear);
            tvMark = (TextView) itemView.findViewById(R.id.tvMark);
            tvProof = (TextView) itemView.findViewById(R.id.tvProof);
            tvUNC = (TextView) itemView.findViewById(R.id.tvUNC);
            tvFine = (TextView) itemView.findViewById(R.id.tvFine);
            tvGood = (TextView) itemView.findViewById(R.id.tvGood);
            rlHolder = (RelativeLayout) itemView.findViewById(R.id.rlHolder);
//            rlHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }
}
