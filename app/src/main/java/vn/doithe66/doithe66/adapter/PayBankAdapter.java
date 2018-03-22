package vn.doithe66.doithe66.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.BankType;

/**
 * Created by Windows 10 Now on 1/19/2018.
 */

public class PayBankAdapter extends RecyclerView.Adapter<PayBankAdapter.PayBankViewHoder> {
    Context context;
    ArrayList<BankType> lisBankType;
    OnClickItemBank onClickItemBank;

    public PayBankAdapter(Context context, ArrayList<BankType> lisBankType,
            OnClickItemBank onClickItemBank) {
        this.context = context;
        this.lisBankType = lisBankType;
        this.onClickItemBank = onClickItemBank;
    }

    public void updateAll() {
        for (BankType bankType : lisBankType) {
            bankType.setbIsClick(false);
        }
        notifyDataSetChanged();
    }

    @Override
    public PayBankViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(context).inflate(R.layout.item_recycle_pay_bank, parent, false);
        return new PayBankViewHoder(view);
    }

    @Override
    public void onBindViewHolder(PayBankViewHoder holder, int position) {
        Picasso.with(context)
                .load(lisBankType.get(position).getLogoOfBank())
                .into(holder.imgRecyclePayBank);
        BankType bankType = lisBankType.get(position);
        if (bankType.isbIsClick()) {
            holder.rbItemRecyclePayBank.setChecked(true);
        } else {
            holder.rbItemRecyclePayBank.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return lisBankType.size();
    }

    class PayBankViewHoder extends RecyclerView.ViewHolder {
        RadioButton rbItemRecyclePayBank;
        ImageView imgRecyclePayBank;

        public PayBankViewHoder(View itemView) {
            super(itemView);
            rbItemRecyclePayBank = itemView.findViewById(R.id.rb_item_recycle);
            imgRecyclePayBank = itemView.findViewById(R.id.img_item_recycle_pay_bank);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemBank.onClickBank(getAdapterPosition());
                }
            });
        }
    }

    public interface OnClickItemBank {
        void onClickBank(int position);
    }
}
