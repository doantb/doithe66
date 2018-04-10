package vn.doithe66.doithe66.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.ItemCard;

/**
 * Created by Windows 10 Now on 1/10/2018.
 */

public class PickTypeCardAdapter extends RecyclerView.Adapter<PickTypeCardAdapter.ViewHolder> {
    private List<ItemCard> data;
//    private onClickProviderId mOnClickProviderId;

    public PickTypeCardAdapter(List<ItemCard> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_type_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(data.get(position).getImgLogo())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

//    public interface onClickProviderId {
//        void getProviderId(int position);
//    }
}
