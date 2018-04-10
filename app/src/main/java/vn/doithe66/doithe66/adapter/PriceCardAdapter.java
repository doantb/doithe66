package vn.doithe66.doithe66.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.Amount;

/**
 * Created by Administrator on 3/23/2018.
 */

public class PriceCardAdapter extends RecyclerView.Adapter<PriceCardAdapter.PriceViewHolder> {
    private Context mContext;
    private ArrayList<Amount> listPrice = new ArrayList<>();
    private onHandleClickPrice mOnHandleClick;

    public PriceCardAdapter(Context mContext, ArrayList<Amount> sPrice) {
        this.mContext = mContext;
        this.listPrice = sPrice;
    }

    @Override
    public PriceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_price, parent, false);
        return new PriceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PriceViewHolder holder, final int position) {
        Amount price = listPrice.get(position);
        holder.txtPrice.setText(price.getCardName());
        if (price.isWatch()) {
            holder.txtPrice.setBackgroundResource(R.drawable.bg_price_hover);
            holder.txtPrice.setTextColor(ContextCompat.getColor(mContext, R.color.color_white));
        } else {
            holder.txtPrice.setBackgroundResource(R.drawable.bg_price);
            holder.txtPrice.setTextColor(ContextCompat.getColor(mContext, R.color.color_black));
        }
        holder.txtPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnHandleClick.onClickPrice(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPrice.size();
    }

    public void setmOnHandleClick(onHandleClickPrice mOnHandleClick) {
        this.mOnHandleClick = mOnHandleClick;
    }

    public static class PriceViewHolder extends RecyclerView.ViewHolder {
        private TextView txtPrice;

        public PriceViewHolder(View itemView) {
            super(itemView);
            txtPrice = itemView.findViewById(R.id.txt_price);
        }
    }

    public interface onHandleClickPrice {
        void onClickPrice(int position);
    }

    public void addAll(ArrayList<Amount> listPrice) {
        this.listPrice = listPrice;
        notifyDataSetChanged();
    }
}
