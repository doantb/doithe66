package vn.doithe66.doithe66.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.Utils;
import vn.doithe66.doithe66.model.ResultCard;
import vn.doithe66.doithe66.model.ResultCardDoithe;

/**
 * Created by Dell Precision on 12/23/2017.
 */

public class RvCardBuySuccessAdapter
        extends RecyclerView.Adapter<RvCardBuySuccessAdapter.CardBuySuccessViewHoder> {
    private Context context;
    private ArrayList<ResultCardDoithe.DataBean> lisCardBuySuccess;
    private onClickCopy mOnClickCopy;

    public void setOnClickCopy(onClickCopy onClickCopy) {
        mOnClickCopy = onClickCopy;
    }

    public RvCardBuySuccessAdapter(Context context, ArrayList<ResultCardDoithe.DataBean> lisCardBuySuccess) {
        this.context = context;
        this.lisCardBuySuccess = lisCardBuySuccess;
    }

    @Override
    public CardBuySuccessViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_rv_confirm_buydone, parent, false);
        return new CardBuySuccessViewHoder(view);
    }

    @Override
    public void onBindViewHolder(CardBuySuccessViewHoder holder, int position) {
        holder.tvCountCardSuccess.setText("" + (position + 1));
        String s = lisCardBuySuccess.get(position).getPinCode();
        int pos = Utils.posSpecial(lisCardBuySuccess.get(position).getPinCode());
        if (pos!=1){
            String s1 = s.substring(0,pos);
            holder.tvPassCardSuccess.setText(s1);
        }
        holder.txtSerial.setText(lisCardBuySuccess.get(position).getSerial());
    }

    private String makeCodeCard(String oldCode) {
        oldCode.replace(" ", "/");
        int endPos = oldCode.indexOf("/");
        return oldCode.substring(0, endPos);
    }

    @Override
    public int getItemCount() {
        return lisCardBuySuccess.size();
    }

    class CardBuySuccessViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvCountCardSuccess;
        private TextView tvPassCardSuccess;
        private TextView txtSerial;
        private Button btnCopy;

        public CardBuySuccessViewHoder(View itemView) {
            super(itemView);
            tvCountCardSuccess = itemView.findViewById(R.id.tv_count_card);
            tvPassCardSuccess = itemView.findViewById(R.id.tv_pass_card_done);
            txtSerial = itemView.findViewById(R.id.txt_serial);
            btnCopy = itemView.findViewById(R.id.btn_copy_spin_code);
            btnCopy.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnClickCopy.onCopy(getAdapterPosition());
        }
    }

    public interface onClickCopy {
        public void onCopy(int position);
    }
}
