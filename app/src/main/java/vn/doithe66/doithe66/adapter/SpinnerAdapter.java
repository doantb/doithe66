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
import android.widget.TextView;
import java.util.ArrayList;
import vn.doithe66.doithe66.R;

/**
 * Created by Windows 10 Now on 11/14/2017.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> arrChoiceRhythm;

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource,
            ArrayList<String> arrChoiceRhythm) {
        super(context, resource, arrChoiceRhythm);
        this.context = context;
        this.arrChoiceRhythm = arrChoiceRhythm;
    }

    public int getCount() {
        return arrChoiceRhythm.size();
    }

    public String getItem(int position) {
        return arrChoiceRhythm.get(position).toString();
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
        String s = arrChoiceRhythm.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false);
        TextView txtChoiceSpan = (TextView) view.findViewById(R.id.txt_choice_span);
        txtChoiceSpan.setText(s);
        return view;
    }
}
