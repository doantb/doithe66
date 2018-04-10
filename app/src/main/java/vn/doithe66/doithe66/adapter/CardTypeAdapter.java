package vn.doithe66.doithe66.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.ItemCard;


/**
 * Created by Windows 10 Now on 11/14/2017.
 */

public class CardTypeAdapter extends RecyclerView.Adapter<CardTypeAdapter.CardTypeViewHolder> {
    private Context mContext;
    private ArrayList<ItemCard> itemCards;
    private onHandleClick mOnHandleClick;

    public CardTypeAdapter(Context context, ArrayList<ItemCard> itemCards) {
        mContext = context;
        this.itemCards = itemCards;
    }

    @Override
    public CardTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_card_type, parent, false);
        return new CardTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardTypeViewHolder holder, final int position) {
        ItemCard itemCard = itemCards.get(position);
        Glide.with(mContext).load(itemCard.getImgLogo()).apply(RequestOptions.circleCropTransform().centerInside()).into(holder.imgCardType);
        if (itemCard.isWatch()) {
            holder.imgCardType.setBackgroundResource(R.drawable.bg_card_hover);
        } else {
            holder.imgCardType.setBackgroundResource(R.drawable.bg_card_type);
        }
        holder.imgCardType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyDataSetChanged();
                mOnHandleClick.onClickCardType(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCards.size();
    }

    public void setOnHandleClick(onHandleClick onHandleClick) {
        mOnHandleClick = onHandleClick;
    }

    public class CardTypeViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCardType;

        public CardTypeViewHolder(View itemView) {
            super(itemView);
            imgCardType = itemView.findViewById(R.id.img_card_type);
        }
    }

    public interface onHandleClick {
        void onClickCardType(int position);
    }

    public void addAll(ArrayList<ItemCard> itemCards) {
        this.itemCards = itemCards;
        notifyDataSetChanged();
    }
}
