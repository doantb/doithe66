package vn.doithe66.doithe66.config;

import android.app.ProgressDialog;

/**
 * Created by Dell Precision on 11/28/2017.
 */

public class ConfigProgressDialog {

    public static void showProgressDialog(ProgressDialog progressDialog, String message){
        if (!progressDialog.isShowing()){
            progressDialog.setMessage(message);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }
    }
    public static void dismissProgressDialog(ProgressDialog progressDialog){
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
