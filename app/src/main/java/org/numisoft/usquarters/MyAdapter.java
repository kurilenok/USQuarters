package org.numisoft.usquarters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukolka on 14.08.16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    List<Coins.Coin> coins = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
        coins = new Coins(context).getCoins();
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


        if (coins.get(position).getName().equalsIgnoreCase("Gettysburg") ||
                coins.get(position).getName().equalsIgnoreCase("Saratoga")) {
            holder.rlHolder.setBackground(context.getDrawable(R.drawable.backgr));
//            holder.tvName.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
//            holder.tvName.setTextColor(context.getResources().getColor(R.color.white));

        } else {
//            holder.tvName.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//            holder.tvName.setTextColor(context.getResources().getColor(R.color.textPrimary));
        }


    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCoin;
        TextView tvName;
        RelativeLayout rlHolder;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCoin = (ImageView) itemView.findViewById(R.id.ivCoin);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            rlHolder = (RelativeLayout) itemView.findViewById(R.id.rlHolder);
        }
    }
}
