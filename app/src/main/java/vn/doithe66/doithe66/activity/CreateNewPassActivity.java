package vn.doithe66.doithe66.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.HomeActivity;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.Utils.Utils;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.RegisterMDResult;

import static vn.doithe66.doithe66.Utils.Constant.FULL_NAME;
import static vn.doithe66.doithe66.Utils.Constant.PASS_LV1;
import static vn.doithe66.doithe66.Utils.Constant.PASS_LV2;

/**
 * Created by Windows 10 Now on 1/26/2018.
 */

public class CreateNewPassActivity extends BaseActivity {
    @BindView(R.id.ab_img_button_back)
    ImageButton mAbImgButtonBack;
    @BindView(R.id.ab_txt_title_action_bar)
    TextView mAbTxtTitleActionBar;
    @BindView(R.id.ab_img_button_confirm)
    ImageView mAbImgButtonConfirm;
    @BindView(R.id.my_progess_bar)
    RelativeLayout mMyProgessBar;
    @BindView(R.id.edt_old_password_lv1)
    EditText edtOldPasswordLv1;
    @BindView(R.id.ll_enter_old_pass_lv1)
    LinearLayout llEnterOldPassLv1;
    private EditText edtNewPass;
    private EditText edtOldPass;
    private onHandleClickChecked mChecked;
    private RegisterMDResult jsonResult;
    private String token;
    private LinearLayout llEnterOldPass;
    private Button btnCreateAccoount;
    private View view;
    private String fromPass;

    public void setChecked(onHandleClickChecked checked) {
        mChecked = checked;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_new_pass;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        edtNewPass = findViewById(R.id.edt_new_password);
        edtOldPass = findViewById(R.id.edt_old_password);
        llEnterOldPass = findViewById(R.id.ll_enter_old_pass);
        btnCreateAccoount = findViewById(R.id.btn_creatNewAccount);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mAbTxtTitleActionBar.setText("Tạo mật khẩu mới");
        Intent intent = getIntent();
        final String fullName = intent.getStringExtra(FULL_NAME);
        fromPass = intent.getStringExtra(Constant.TYPE_PASS);
        token = intent.getStringExtra(Constant.KEY_TOKEN);
        if (fromPass.equalsIgnoreCase(PASS_LV2)) {
            llEnterOldPassLv1.setVisibility(View.VISIBLE);
            edtOldPass.setHint("Nhập mật khẩu cấp 2 cũ");
            edtNewPass.setHint("Nhập mật khẩu cấp 2 mới");
        }
        if (intent.getIntExtra("key_acccess", 0) == 2) {
            llEnterOldPass.setVisibility(View.GONE);
            btnCreateAccoount.setVisibility(View.VISIBLE);
            btnCreateAccoount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loginWithNewGmail(token, fullName, edtNewPass.getText().toString());
                }
            });
        }
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        btnCreateAccoount.setOnClickListener(mOnProfileClickListener);
    }

    private View.OnClickListener mOnProfileClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Utils.closeKeyboard(getApplicationContext(),edtNewPass.getWindowToken());
            mMyProgessBar.setVisibility(View.VISIBLE);
            if (edtNewPass.getText().toString().equals("")) {
                Toast.makeText(CreateNewPassActivity.this, "Vui lòng nhập password",
                        Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = getIntent();
                if (intent != null) {
                    newPassRetrofit(edtOldPass.getText().toString(),
                            edtNewPass.getText().toString());
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.ab_img_button_back, R.id.ab_img_button_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ab_img_button_back:
                super.onBackPressed();
                break;
            case R.id.ab_img_button_confirm:
                finish();
                break;
        }
    }

    public interface onHandleClickChecked {
        void onClickCheck(String oldPass, String newPass);
    }

    private void newPassRetrofit(String oldPass, String newPass) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
        if (fromPass.equalsIgnoreCase(PASS_LV1)) {
            retrofit.create(InterfaceAPI.class)
                    .createNewPassLv1(oldPass, newPass)
                    .enqueue(new Callback<RegisterMDResult>() {
                        @Override
                        public void onResponse(Call<RegisterMDResult> call,
                                               Response<RegisterMDResult> response) {
                            jsonResult = response.body();
                            chooseRepCode(token);
                        }

                        @Override
                        public void onFailure(Call<RegisterMDResult> call, Throwable t) {

                        }
                    });
        } else {
            retrofit.create(InterfaceAPI.class)
                    .createNewPassLv2(edtOldPasswordLv1.getText().toString(), oldPass, newPass)
                    .enqueue(new Callback<RegisterMDResult>() {
                        @Override
                        public void onResponse(Call<RegisterMDResult> call,
                                               Response<RegisterMDResult> response) {
                            jsonResult = response.body();
                            chooseRepCode(token);
                        }

                        @Override
                        public void onFailure(Call<RegisterMDResult> call, Throwable t) {

                        }
                    });
        }
    }

    private void chooseRepCode(String token) {
        switch (jsonResult.getRepcode()) {
            case 0:
                Toast.makeText(this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "Bạn đã thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                break;
            case -1:
                Toast.makeText(this, "Đăng nhập hết hạn", Toast.LENGTH_SHORT).show();
                break;
        }
        mMyProgessBar.setVisibility(View.GONE);
    }

    private void loginWithNewGmail(final String token, String fullName, String passWord) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance();
        //
        //        Log.d("token", token + "");
        //        Log.d("token", passWord + "");
        //        Log.d("token", fullName + "");
        retrofit.create(InterfaceAPI.class)
                .loginWithGoogle(token, fullName, passWord)
                .enqueue(new Callback<RegisterMDResult>() {
                    @Override
                    public void onResponse(Call<RegisterMDResult> call,
                                           Response<RegisterMDResult> response) {
                        jsonResult = response.body();
                        Toast.makeText(CreateNewPassActivity.this, jsonResult.getMessage() + "", Toast.LENGTH_SHORT).show();
                        saveAccessToken(jsonResult.getToken());
                        startActivity(HomeActivity.class);
                    }

                    @Override
                    public void onFailure(Call<RegisterMDResult> call, Throwable t) {
                        Toast.makeText(CreateNewPassActivity.this, "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveAccessToken(String token) {
        SharedPrefs.getInstance().put(Constant.ACCESS_TOKEN, token);
    }
}
