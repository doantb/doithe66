package vn.doithe66.doithe66.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.DataResultHistory;
import vn.doithe66.doithe66.model.HistoryTransactionDetail;

/**
 * Created by Windows 10 Now on 11/29/2017.
 */

public class HistoryDetailAdapter extends RecyclerView.Adapter<HistoryDetailAdapter.HistoryTranViewHolder> {
    private Context mContext;
    private ArrayList<HistoryTransactionDetail> mTransactions = new ArrayList<>();
    private static int currentPosition = 0;
    private static boolean iCheck = false;
    private static int iSo = 0;
    private ArrayList<DataResultHistory> lisHistory;

    public HistoryDetailAdapter(Context context, ArrayList<DataResultHistory> lisHistory) {
        mContext = context;
        this.lisHistory = lisHistory;
    }

    @Override
    public HistoryTranViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_card_view, parent, false);
        return new HistoryTranViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryTranViewHolder holder, final int position) {
        DataResultHistory dataResultHistory = lisHistory.get(position);

        holder.tvTypeTransDetaiHistory.setText(setDataNull(dataResultHistory.getType()));
//        Log.d("setdata", "onBindViewHolder: " + setDataNull(dataResultHistory.getType()));
        holder.tvDiscountDetailHistory.setText(setDataNull(dataResultHistory.getDiscount()));
        holder.tvPhoneDetailHistory.setText(setDataNull(dataResultHistory.getPhone()));
        holder.tvEmailDetailHistory.setText(setDataNull(dataResultHistory.getEmail()));
        holder.tvAmountDetailHistory.setText(setDataNull(dataResultHistory.getStrAmount() + " đ"));
        holder.tvTypeCarddetailHistory.setText(setDataNull(dataResultHistory.getTelcoCode()));
        holder.tvStatusDetailHistory.setText(setDataNull(dataResultHistory.getStatus()));
        holder.txtNameTran.setText(String.valueOf("Giao dịch " + (position + 1)));

        holder.txtTimeTran.setText(setDataNull(dataResultHistory.getCreateDate()));
        holder.txtPriceTran.setText(setDataNull(dataResultHistory.getStrPrice()));
        holder.imgDropUp.setVisibility(View.GONE);
        holder.imgDropDown.setVisibility(View.VISIBLE);
        holder.llTranDetail.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation

            //toggling visibility
            if (currentPosition == iSo && !iCheck) {
                holder.llTranDetail.setVisibility(View.GONE);
            } else {
                Animation slideDown = AnimationUtils.loadAnimation(mContext, R.anim.slide_down);
                holder.llTranDetail.setVisibility(View.VISIBLE);
                holder.llTranDetail.startAnimation(slideDown);
                holder.imgDropUp.setVisibility(View.VISIBLE);
                holder.imgDropDown.setVisibility(View.GONE);
            }


            //adding sliding effect
        }

        holder.rlItemTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                iSo = currentPosition;
                currentPosition = position;
                iCheck = !iCheck;

                //reloding the list
                notifyDataSetChanged();
            }
        });
    }

    private String setDataNull(String string) {
        if (string.equalsIgnoreCase("null")) {
            return "Không có dữ liệu";
        } else {
            return string;
        }
    }

    @Override
    public int getItemCount() {
        if (lisHistory != null) {
            return lisHistory.size();
        }
        return 0;
    }

    public static class HistoryTranViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNameTran;
        private TextView txtTimeTran;
        private TextView txtPriceTran;
        private LinearLayout llTranDetail;
        private RelativeLayout rlItemTran;
        private ImageView imgDropUp, imgDropDown;

        private TextView tvTypeTransDetaiHistory;
        private TextView tvEmailDetailHistory;
        private TextView tvAmountDetailHistory;
        private TextView tvTypeCarddetailHistory;
        private TextView tvStatusDetailHistory;
        private TextView tvPhoneDetailHistory;
        private TextView tvDiscountDetailHistory;


        public HistoryTranViewHolder(View itemView) {
            super(itemView);
            txtNameTran = itemView.findViewById(R.id.txt_name_transaction);
            txtTimeTran = itemView.findViewById(R.id.txt_time_transaction);
            txtPriceTran = itemView.findViewById(R.id.txt_price_tran);
            llTranDetail = itemView.findViewById(R.id.ll_tran_detail);
            rlItemTran = itemView.findViewById(R.id.rl_item_tran);
            imgDropDown = itemView.findViewById(R.id.img_drop_down);
            imgDropUp = itemView.findViewById(R.id.img_drop_up);

            tvTypeTransDetaiHistory = itemView.findViewById(R.id.tv_type_history_detail_transaction);
            tvEmailDetailHistory = itemView.findViewById(R.id.tv_email_history_detail);
            tvAmountDetailHistory = itemView.findViewById(R.id.tv_amount_history_detail);
            tvTypeCarddetailHistory = itemView.findViewById(R.id.tv_typecard_history_detail);
            tvStatusDetailHistory = itemView.findViewById(R.id.tv_status_history_detail);
            tvPhoneDetailHistory = itemView.findViewById(R.id.tv_phone_history_detail);
            tvDiscountDetailHistory = itemView.findViewById(R.id.tv_discount_history_detail);
        }
    }
}
