package vn.doithe66.doithe66.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.activity.CreateNewPassActivity;
import vn.doithe66.doithe66.config.ConfigProgressDialog;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.RegisterMDResult;
import vn.doithe66.doithe66.model.ResultJsonApi;
import vn.doithe66.doithe66.model.UserInfo;

import static vn.doithe66.doithe66.Utils.Constant.ACCESS_TOKEN;
import static vn.doithe66.doithe66.Utils.Constant.KEY_TOKEN;

/**
 * Created by Dell Precision on 12/08/2017.
 */

public class ForgetPassFragmentDialog extends BaseFragmentDialog {
    @BindView(R.id.edt_forget_pass_Email)
    EditText edtForgetPassEmail;
    @BindView(R.id.edt_forget_pass_lv2)
    EditText edtForgetPassLv2;
    @BindView(R.id.btn_forget_password)
    Button btnForgetPassEmail;
    @BindView(R.id.btn_cancel_forget_password)
    Button btnCancelForgetPass;
    @BindView(R.id.dl_txt_title)
    TextView dlTxtTitle;
    @BindView(R.id.dl_txt_content)
    TextView dlTxtContent;
    Unbinder unbinder;
    @BindView(R.id.dl_rb_get_pass_lv1)
    RadioButton dlRbGetPassLv1;
    @BindView(R.id.dl_rb_get_pass_lv2)
    RadioButton dlRbGetPassLv2;
    @BindView(R.id.dl_ln_get_pass)
    LinearLayout dlLnGetPass;

    private String sEmailReceivePass;
    private boolean fromLogin = false;
    private String token;

    private int iRepCode = 0;

    private ProgressDialog progressDialog;

    public static ForgetPassFragmentDialog newInstance(String fromActivity) {
        ForgetPassFragmentDialog forgetPassFragmentDialog = new ForgetPassFragmentDialog();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FROM_ACTIVITY, fromActivity);
        forgetPassFragmentDialog.setArguments(bundle);
        return forgetPassFragmentDialog;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_dialog_forgetpassword;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        token = SharedPrefs.getInstance().get(ACCESS_TOKEN, String.class);
        ButterKnife.bind(this, mRoot);
        // Xoa khoang trang tren cung cua Dialog:
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        progressDialog = new ProgressDialog(getActivity());
        if (getArguments().getString(Constant.FROM_ACTIVITY).equalsIgnoreCase(Constant.LOGIN_ACTIVITY)) {
            fromLogin = true;
            dlRbGetPassLv1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    edtForgetPassLv2.setVisibility(View.VISIBLE);
                }
            });
            dlRbGetPassLv2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    edtForgetPassLv2.setVisibility(View.GONE);
                }
            });
        } else {
            dlLnGetPass.setVisibility(View.GONE);
            dlTxtTitle.setText("Đổi mật khẩu");
            dlTxtContent.setText("Bạn có thể chọn đổi mật khẩu cấp 1 hoặc cấp 2");
            edtForgetPassEmail.setVisibility(View.GONE);
            btnCancelForgetPass.setText("Đổi mật khẩu cấp 1");
            btnForgetPassEmail.setText("Đổi mật khẩu cấp 2");
        }
    }

    @OnClick({R.id.btn_forget_password, R.id.btn_cancel_forget_password})
    public void onClickButterKnife(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_password:
                if (fromLogin) {
                    getForgetPassWord();
                } else {
                    Intent intent = new Intent(getActivity(), CreateNewPassActivity.class);
                    intent.putExtra(Constant.TYPE_PASS, Constant.PASS_LV2);
                    intent.putExtra(KEY_TOKEN, token);
                    startActivity(intent);
                }
                break;
            case R.id.btn_cancel_forget_password:
                if (fromLogin) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    showConfirmAlertDialog(builder, getDialog());
                } else {
                    Intent intent = new Intent(getActivity(), CreateNewPassActivity.class);
                    intent.putExtra(Constant.TYPE_PASS, Constant.PASS_LV1);
                    intent.putExtra(KEY_TOKEN, token);
                    startActivity(intent);
                }
                break;
        }
    }

    public void getForgetPassWord() {
        sEmailReceivePass = edtForgetPassEmail.getText().toString().trim();
        String passlv1 = edtForgetPassLv2.getText().toString();
        if (sEmailReceivePass.isEmpty()) {
            edtForgetPassEmail.setError("Vui lòng điền đúng Email đã đăng ký !");
        } else if (!validEmail(sEmailReceivePass)) {
            edtForgetPassEmail.setError("Vui lòng điền đúng định dạng Email !");
        } else {
            if (dlRbGetPassLv1.isChecked()) {
                forgetPassWordRetrofit(sEmailReceivePass);
            } else {
                if (sEmailReceivePass.isEmpty()) {
                    edtForgetPassEmail.setError("Vui lòng nhập mật khẩu cấp 1!");
                } else {
                    forgetPassWordLv2Retrofit(sEmailReceivePass, passlv1);
                }
            }
        }
    }

    private void forgetPassWordLv2Retrofit(final String sEmailReceivePass, final String passlv1) {
        ConfigProgressDialog.showProgressDialog(progressDialog, "Loading ... !");
        Retrofit retrofit = ConfigRetrofitApi.getInstance();
        retrofit.create(InterfaceAPI.class)
                .login(sEmailReceivePass, passlv1)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() != null) {
                            final String token = response.body();
                            if (token != null) {
                                Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
                                retrofit.create(InterfaceAPI.class)
                                        .getForgetPassWordlv2(sEmailReceivePass, passlv1)
                                        .enqueue(new Callback<RegisterMDResult>() {
                                            @Override
                                            public void onResponse(Call<RegisterMDResult> call,
                                                                   Response<RegisterMDResult> response) {
                                                if (response != null) {
                                                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(getActivity(), "Không nhận được thông tin từ API !",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                                ConfigProgressDialog.dismissProgressDialog(progressDialog);
                                            }

                                            @Override
                                            public void onFailure(Call<RegisterMDResult> call, Throwable t) {
                                                ConfigProgressDialog.dismissProgressDialog(progressDialog);
                                            }
                                        });
                            }
                        } else {
                            Toast.makeText(getActivity(), "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                            ConfigProgressDialog.dismissProgressDialog(progressDialog);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                    }
                });
    }

    public void forgetPassWordRetrofit(String sEmail) {
        ConfigProgressDialog.showProgressDialog(progressDialog, "Loading ... !");
        Retrofit retrofit = ConfigRetrofitApi.getInstance();
        retrofit.create(InterfaceAPI.class)
                .getForgetPassWordByEmail(sEmail)
                .enqueue(new Callback<RegisterMDResult>() {
                    @Override
                    public void onResponse(Call<RegisterMDResult> call,
                                           Response<RegisterMDResult> response) {
                        if (response.body() != null) {
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Không nhận được thông tin từ API !",
                                    Toast.LENGTH_LONG).show();
                        }
                        ConfigProgressDialog.dismissProgressDialog(progressDialog);
                    }

                    @Override
                    public void onFailure(Call<RegisterMDResult> call, Throwable t) {
                        ConfigProgressDialog.dismissProgressDialog(progressDialog);
                    }
                });
    }

    public void checkRepCode(int iRepCode) {
        if (iRepCode == 1) {
            Toast.makeText(getActivity(), "Đã gửi PassWord, vui lòng check email !",
                    Toast.LENGTH_LONG).show();
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

    @OnClick({R.id.dl_rb_get_pass_lv1, R.id.dl_rb_get_pass_lv2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dl_rb_get_pass_lv1:
                break;
            case R.id.dl_rb_get_pass_lv2:
                break;
        }
    }
}
