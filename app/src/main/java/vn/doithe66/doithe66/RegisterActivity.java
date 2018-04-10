package vn.doithe66.doithe66;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.vansuita.gaussianblur.GaussianBlur;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.doithe66.doithe66.Utils.Utils;
import vn.doithe66.doithe66.activity.BaseActivity;
import vn.doithe66.doithe66.presenter.RegisterPresenter;
import vn.doithe66.doithe66.presenter.RegisterPresenterImpl;
import vn.doithe66.doithe66.presenter.RegisterView;
import vn.doithe66.doithe66.view.ProgessView;

/**
 * Created by Windows 10 Now on 12/28/2017.
 */

public class RegisterActivity extends BaseActivity implements ProgessView, RegisterView {

    @BindView(R.id.register_img)
    ImageView mRegisterImg;
    @BindView(R.id.register_txt_user_name)
    EditText mRegisterTxtUserName;
    @BindView(R.id.register_txt_email)
    EditText mRegisterTxtEmail;
    @BindView(R.id.register_txt_phone)
    EditText mRegisterTxtPhone;
    @BindView(R.id.register_txt_pass)
    EditText mRegisterTxtPass;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;
    @BindView(R.id.my_progess_bar)
    RelativeLayout mMyProgessBar;
    @BindView(R.id.register_txt_pass_lv2)
    EditText registerTxtPassLv2;
    private RegisterPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mPresenter = new RegisterPresenterImpl(this, this);
        Bitmap bitmap = GaussianBlur.with(this).render(R.drawable.bg_login);
        mRegisterImg.setImageBitmap(bitmap);
    }

    @OnClick(R.id.register_btn)
    public void onViewClicked() {
        checkRegister();
    }

    private void checkRegister() {
        Utils.closeKeyboard(this, registerTxtPassLv2.getWindowToken());
        String user = mRegisterTxtUserName.getText().toString();
        String phone = mRegisterTxtPhone.getText().toString();
        String email = mRegisterTxtEmail.getText().toString();
        String pass = mRegisterTxtPass.getText().toString();
        String passlv2 = registerTxtPassLv2.getText().toString();
        if (user.equals("") || user == null) {
            mRegisterTxtUserName.setError("Vui lòng nhập họ tên");
        } else if (phone.equals("") || phone == null) {
            mRegisterTxtPhone.setError("Vui lòng nhập số điện thoại");
        } else if (!validEmail(email)) {
            mRegisterTxtEmail.setError("Vui lòng nhập đúng định dạng email");
        } else if (pass.equals("") || pass == null) {
            mRegisterTxtPass.setError("Vui lòng nhập password");
        } else if (passlv2.equals("") || pass == null) {
            mRegisterTxtPass.setError("Vui lòng nhập password cấp 2");
        } else if (pass.length() < 6 || passlv2.length() < 6) {
            Toast.makeText(this, "Vui lòng chọn mật khẩu có độ dài lớn hơn 6 kí tự", Toast.LENGTH_LONG).show();
            return;
        } else {
            mPresenter.register(user, phone, email, pass, passlv2);
        }
    }

    private boolean validEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void showProgess() {
        mMyProgessBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgess() {
        mMyProgessBar.setVisibility(View.GONE);
    }

    @Override
    public void navigationToLogin(String sMessage) {
        Toast.makeText(this, sMessage + "\nBạn vui lòng kiểm tra email để xác nhận đăng kí",
                Toast.LENGTH_LONG).show();
        startActivity(LoginActivity.class);
    }

}
