package vn.doithe66.doithe66.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.BankType;

public class SpinnerChooseBankAdapter extends ArrayAdapter<BankType> {
    private Context context;
    private ArrayList<BankType> arrChoiceRhythm;

    public SpinnerChooseBankAdapter(@NonNull Context context, @LayoutRes int resource,
                                    ArrayList<BankType> arrChoiceRhythm) {
        super(context, resource, arrChoiceRhythm);
        this.context = context;
        this.arrChoiceRhythm = arrChoiceRhythm;
    }

    public int getCount() {
        return arrChoiceRhythm.size();
    }

    public BankType getItem(int position) {
        return arrChoiceRhythm.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView view = new TextView(context);
        view.setGravity(Gravity.CENTER);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        String s = arrChoiceRhythm.get(position).getBankName();
        View view = LayoutInflater.from(context).inflate(R.layout.item_spinner_bank, parent, false);
        TextView txtChoiceSpan = (TextView) view.findViewById(R.id.txt_choice_span);
        ImageView imgChoiceSpan = (ImageView) view.findViewById(R.id.img_avatar_bank);
        txtChoiceSpan.setText(s);
        Glide.with(context).load(arrChoiceRhythm.get(position).getLogoOfBank()).into(imgChoiceSpan);
        return view;
    }
}
