package vn.doithe66.doithe66.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.ItemMenu;

/**
 * Created by Administrator on 3/21/2018.
 */

public class ItemMenuAdapter extends RecyclerView.Adapter<ItemMenuAdapter.ItemMenuViewHolder> {
    private Context context;
    private ArrayList<ItemMenu> itemMenus;
    private OnHandleClickItemMenu onHandleClickItemMenu;

    public ItemMenuAdapter(Context context, ArrayList<ItemMenu> itemMenus) {
        this.context = context;
        this.itemMenus = itemMenus;
    }

    @Override
    public ItemMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new ItemMenuViewHolder(view);
    }

    public void setOnHandleClickItemMenu(OnHandleClickItemMenu onHandleClickItemMenu) {
        this.onHandleClickItemMenu = onHandleClickItemMenu;
    }

    @Override
    public void onBindViewHolder(final ItemMenuViewHolder holder, int position) {
        ItemMenu itemMenu = itemMenus.get(position);
        holder.imgItemMenu.setImageResource(itemMenu.getIconItemMenu());
        holder.txtTitleItemMenu.setText(itemMenu.getTxtTitleItemMenu());
        holder.rlItemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHandleClickItemMenu.onClickItem(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemMenus.size();
    }

    public static class ItemMenuViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_item_menu)
        ImageView imgItemMenu;
        @BindView(R.id.txt_title_item_menu)
        TextView txtTitleItemMenu;
        @BindView(R.id.rl_item_menu)
        LinearLayout rlItemMenu;

        public ItemMenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnHandleClickItemMenu {
        void onClickItem(int position);
    }
}