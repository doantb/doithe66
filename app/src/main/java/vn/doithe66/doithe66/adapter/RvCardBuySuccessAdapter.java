package vn.doithe66.doithe66.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.ResultCard;

/**
 * Created by Dell Precision on 12/23/2017.
 */

public class RvCardBuySuccessAdapter
        extends RecyclerView.Adapter<RvCardBuySuccessAdapter.CardBuySuccessViewHoder> {
    private Context context;
    private ArrayList<ResultCard> lisCardBuySuccess;
    private onClickCopy mOnClickCopy;

    public void setOnClickCopy(onClickCopy onClickCopy) {
        mOnClickCopy = onClickCopy;
    }

    public RvCardBuySuccessAdapter(Context context, ArrayList<ResultCard> lisCardBuySuccess) {
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
        holder.tvPassCardSuccess.setText(lisCardBuySuccess.get(position).getProviderCode());
        holder.txtSerial.setText(lisCardBuySuccess.get(position).getSerial());
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
