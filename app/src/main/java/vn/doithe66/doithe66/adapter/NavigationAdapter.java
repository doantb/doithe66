package vn.doithe66.doithe66.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.ItemNav;

/**
 * Created by Windows 10 Now on 1/8/2018.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigtionViewHolder> {
    private ArrayList<ItemNav> mItemNavs;
    private Context mContext;
    private clickItem mClickItem;

    public NavigationAdapter(ArrayList<ItemNav> itemNavs, Context context) {
        mItemNavs = itemNavs;
        mContext = context;
    }

    @Override
    public NavigtionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rcl_nav, parent, false);
        NavigtionViewHolder viewHolder = new NavigtionViewHolder(view);
        setClickable(view, viewHolder);
        return viewHolder;
    }

    private void setClickable(View view, final NavigtionViewHolder viewHolder) {
        viewHolder.mNavLlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickItem.onClickItemNav(view, viewHolder.getAdapterPosition());
            }
        });
    }

    public void setClickItem(clickItem clickItem) {
        mClickItem = clickItem;
    }

    @Override
    public void onBindViewHolder(NavigtionViewHolder holder, int position) {
        if (mItemNavs == null) return;
        holder.bindView(mItemNavs.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemNavs.size();
    }

    public static class NavigtionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nav_icon)
        ImageView mNavIcon;
        @BindView(R.id.nav_title)
        TextView mNavTitle;
        @BindView(R.id.nav_ll_item)
        LinearLayout mNavLlItem;
        ItemNav mItemNav;

        public NavigtionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(ItemNav itemNav) {
            this.mItemNav = itemNav;
            mNavIcon.setImageResource(itemNav.getIcon());
            mNavTitle.setText(itemNav.getTitle());
        }
    }

    public interface clickItem {
        void onClickItemNav(View view, int position);
    }
}
