package vn.doithe66.doithe66.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.text.ParseException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.config.ConfigProgressDialog;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.ResultJsonApi;

/**
 * Created by Dell Precision on 12/08/2017.
 */

public class ForgetPassFragmentDialog extends BaseFragmentDialog {
    @BindView(R.id.edt_forget_pass_Email)
    EditText edtForgetPassEmail;
    @BindView(R.id.btn_forget_password)
    Button btnForgetPassEmail;
    @BindView(R.id.btn_cancel_forget_password)
    Button btnCancelForgetPass;

    private String sEmailReceivePass;

    private int iRepCode = 0;

    private ProgressDialog progressDialog;

    @Override
    protected int layoutId() {
        return R.layout.fragment_dialog_forgetpassword;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        ButterKnife.bind(this, mRoot);
        // Xoa khoang trang tren cung cua Dialog:
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        progressDialog = new ProgressDialog(getActivity());
    }

    @OnClick({ R.id.btn_forget_password, R.id.btn_cancel_forget_password })
    public void onClickButterKnife(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_password:
                getForgetPassWord();
                break;
            case R.id.btn_cancel_forget_password:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                showConfirmAlertDialog(builder, getDialog());
                break;
        }
    }

    public void getForgetPassWord() {
        sEmailReceivePass = edtForgetPassEmail.getText().toString().trim();
        if (sEmailReceivePass.isEmpty()) {
            edtForgetPassEmail.setError("Vui lòng điền đúng Email đã đăng ký !");
        } else if (!validEmail(sEmailReceivePass)) {
            edtForgetPassEmail.setError("Vui lòng điền đúng định dạng Email !");
        } else {
            forgetPassWordRetrofit(sEmailReceivePass);
        }
    }

    public void forgetPassWordRetrofit(String sEmail) {
        ConfigProgressDialog.showProgressDialog(progressDialog, "Loading ... !");
        Retrofit retrofit = ConfigRetrofitApi.getInstance();
        retrofit.create(InterfaceAPI.class)
                .getForgetPassWordByEmail(sEmail)
                .enqueue(new Callback<ResultJsonApi>() {
                    @Override
                    public void onResponse(Call<ResultJsonApi> call,
                            Response<ResultJsonApi> response) {
                        if (response != null) {
                            ResultJsonApi resultJsonApi = response.body();
                            iRepCode = resultJsonApi.getRepCode();
                            //                    Log.d("iRepCode", "onResponse: " + " " + iRepCode);
                            checkRepCode(iRepCode);
                        } else {
                            Toast.makeText(getActivity(), "Không nhận được thông tin từ API !",
                                    Toast.LENGTH_SHORT).show();
                        }
                        ConfigProgressDialog.dismissProgressDialog(progressDialog);
                    }

                    @Override
                    public void onFailure(Call<ResultJsonApi> call, Throwable t) {
                        ConfigProgressDialog.dismissProgressDialog(progressDialog);
                    }
                });
    }

    public void checkRepCode(int iRepCode) {
        if (iRepCode == 1) {
            Toast.makeText(getActivity(), "Đã gửi PassWord, vui lòng check email !",
                    Toast.LENGTH_SHORT).show();
            getDialog().cancel();
        } else if (iRepCode == -1) {
            Toast.makeText(getActivity(),
                    "Địa chỉ email không tồn tại, vui lòng kiểm tra lại email !",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static void showConfirmAlertDialog(AlertDialog.Builder builder, final Dialog dialog) {
        builder.setTitle("XÁC NHẬN")
                .setMessage("Bạn chắc chắn muốn hủy bỏ chưa ?")
                .setCancelable(false)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

    public static boolean validEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern)) {
            return true;
        } else {
            return false;
        }
    }
}
