package vn.doithe66.doithe66.config;

import android.content.Context;
import android.os.Build;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.Utils.Utils;
import vn.doithe66.doithe66.adapter.SpinnerAdapter;
import vn.doithe66.doithe66.adapter.SpinnerChooseBankAdapter;
import vn.doithe66.doithe66.model.BankType;

/**
 * Created by Dell Precision on 11/29/2017.
 */

public class ConfigSpinner {
    //    public static final String NAP_TIEN_DIEN_THOAI = "nap_tien_cho_dien_thoai";

    // spn typeload money
    //    public static void setupSpinnerTypeLoadMoney(ArrayList<String> lisTypeLoadMoney,
    //            SpinnerAdapter spinnerAdapter, Spinner spinnerTypeLoadMoney,
    //            Context context, boolean b, String from) {
    //        //        if (from.equalsIgnoreCase(NAP_TIEN_DIEN_THOAI))
    //        //        {
    //        //            lisTypeLoadMoney.add("Kiểu thanh toán: Trả trước");
    //        //        }else
    //        //        if (b) {
    //        lisTypeLoadMoney.add("Kiểu thanh toán: Trả trước");
    //        lisTypeLoadMoney.add("Kiểu thanh toán: Trả sau");
    //        //        } else {
    //        //            lisTypeLoadMoney.add("Kiểu thanh toán: Trả sau");
    //        //        }
    //
    //        arrayAdapterTypeLoadMoney =
    //                new ArrayAdapter<>(context, android.R.layout.simple_list_item_activated_1,
    //                        lisTypeLoadMoney);
    //        spinnerTypeLoadMoney.setAdapter(arrayAdapterTypeLoadMoney);
    //    }

    // spn chose price custom:
    public static void setupSpinnner(ArrayList<String> listSpinner,
                                     SpinnerAdapter spinnerAdapter, Spinner spinnerChosePrice, Context context,
                                     String typeSpinner) {
        if (typeSpinner.equalsIgnoreCase(Constant.TYPE_SPINNER)) {
            listSpinner.add("Kiểu thanh toán: Trả trước");
            listSpinner.add("Kiểu thanh toán: Trả sau");
        } else {
            listSpinner.add("10.000 đ");
            listSpinner.add("20.000 đ");
            listSpinner.add("30.000 đ");
            listSpinner.add("50.000 đ");
            listSpinner.add("100.000 đ");
            listSpinner.add("200.000 đ");
            listSpinner.add("300.000 đ");
            listSpinner.add("500.000 đ");
        }
        spinnerAdapter =
                new SpinnerAdapter(context, android.R.layout.simple_spinner_item, listSpinner);
        spinnerChosePrice.setAdapter(spinnerAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            spinnerChosePrice.setDropDownVerticalOffset(Utils.dpToPx(50));
        }
    }

    public static void setupSpinnnerBank(Context context, Spinner spinnerChoseBank, SpinnerChooseBankAdapter spinnerAdapter, ArrayList<BankType> bankTypes) {
        spinnerAdapter =
                new SpinnerChooseBankAdapter(context, android.R.layout.simple_spinner_item, bankTypes);
        spinnerChoseBank.setAdapter(spinnerAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            spinnerChoseBank.setDropDownVerticalOffset(Utils.dpToPx(50));
        }
    }
}
