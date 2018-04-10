package vn.doithe66.doithe66;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.Utils.Utils;
import vn.doithe66.doithe66.activity.BaseActivity;
import vn.doithe66.doithe66.activity.CreateNewPassActivity;
import vn.doithe66.doithe66.fragment.ForgetPassFragmentDialog;
import vn.doithe66.doithe66.networking.TaskGetGG;
import vn.doithe66.doithe66.presenter.LoginActivityPresenter;
import vn.doithe66.doithe66.presenter.LoginPresenterImpl;
import vn.doithe66.doithe66.view.LoginActivityView;
import vn.doithe66.doithe66.view.ProgessView;

import static vn.doithe66.doithe66.Utils.Constant.FULL_NAME;
import static vn.doithe66.doithe66.Utils.Constant.KEY_TOKEN;

/**
 * Created by Windows 10 Now on 12/28/2017.
 */

public class LoginActivity extends BaseActivity
        implements ProgessView, LoginActivityView, GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.edt_user_name)
    EditText mEdtUserName;
    @BindView(R.id.edt_password)
    EditText mEdtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.login_btn_gg)
    Button mLoginBtnGg;
    //    @BindView(R.id.login_btn_fb)
    //    Button mLoginBtnFb;
    @BindView(R.id.login_txt_creat_account)
    TextView mLoginTxtCreatAccount;
    @BindView(R.id.login_txt_forgot_pass)
    TextView mLoginTxtForgotPass;
    @BindView(R.id.my_progess_bar)
    RelativeLayout mMyProgessBar;
    private LoginActivityPresenter mPresenter;
    private GoogleApiClient mGoogleApiClient;
    private TaskGetGG mTaskGetGG;
    private static final int RC_SIGN_IN = 2018;
    public static final String RC_SIGN_OUT = "sign_out";
    private GoogleSignInOptions gso;
    private String tokenGmail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        hiddenInputType();
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mPresenter = new LoginPresenterImpl(this, this);
        View view = this.getCurrentFocus();
        configGmail();
    }

    @OnClick({
            R.id.btn_login, R.id.login_btn_gg, R.id.login_txt_creat_account,
            R.id.login_txt_forgot_pass
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Utils.closeKeyboard(this,mEdtPassword.getWindowToken());
                mPresenter.loginMuatheAccount(mEdtUserName.getText().toString(),
                        mEdtPassword.getText().toString());
                break;
            case R.id.login_btn_gg:
                signIn(this);
                break;
            //            case R.id.login_btn_fb:
            //                Toast.makeText(this, "Chức năng này hiện đang phát triển", Toast.LENGTH_LONG)
            //                        .show();
            //                break;
            case R.id.login_txt_creat_account:
                startActivity(RegisterActivity.class);
                break;
            case R.id.login_txt_forgot_pass:
                ForgetPassFragmentDialog forgetPassFragmentDialog = ForgetPassFragmentDialog.newInstance(Constant.LOGIN_ACTIVITY);
                forgetPassFragmentDialog.show(getSupportFragmentManager(),
                        "forgetPassFragmentDialog1");
                forgetPassFragmentDialog.setCancelable(false);
                break;
        }
    }

    private void configGmail() {
        Intent intent1 = getIntent();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestScopes(
                new Scope(Scopes.DRIVE_APPFOLDER))
                .requestServerAuthCode(getString(R.string.server_client_id))
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = getmGoogleApiClient(this);
        if (intent1.getIntExtra(RC_SIGN_OUT, 0) == 1) {
            //            revokeAccess();
            //            signOut(mGoogleApiClient, this, this);
            SignOut();
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
    public void setUserNameError() {
        mEdtUserName.setError("Sai thông tin tài khoản");
    }

    @Override
    public void setPassWordError() {
        mEdtPassword.setError("Sai thông tin mật khẩu");
    }

    @Override
    public void toastErrorLogin(String error) {
        Toast.makeText(this, error + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        startActivity(HomeActivity.class);
        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void chooseRepCode(int repCode) {
        switch (repCode) {
            case 0:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent3 = new Intent(this, HomeActivity.class);
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                startActivity(intent3);
                break;
            case 2:
                Intent intent2 = new Intent(this, CreateNewPassActivity.class);
                intent2.putExtra(KEY_TOKEN, tokenGmail);
                intent2.putExtra(FULL_NAME,
                        SharedPrefs.getInstance().get(Constant.USER_NAME, String.class));
                intent2.putExtra("key_acccess", 2);
                startActivity(intent2);
                break;
            case 3:
                Toast.makeText(this, "Tạo tài khoản thành công", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(this, HomeActivity.class);
                intent1.putExtra(KEY_TOKEN,
                        SharedPrefs.getInstance().get(Constant.ACCESS_TOKEN, String.class));
                startActivity(intent1);
                break;
            case 4:
                Toast.makeText(this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void signIn(GoogleApiClient.OnConnectionFailedListener failedListener) {
        if (mGoogleApiClient == null) {
            getmGoogleApiClient(failedListener);
        }
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        //        Log.e("signIn", "co vao day");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        handleSignInResult(requestCode, data, this);
        Log.e("onActivityResult", "co vao day");
    }

    private GoogleApiClient getmGoogleApiClient(
            GoogleApiClient.OnConnectionFailedListener failedListener) {
        if (mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {
            return new GoogleApiClient.Builder(this).enableAutoManage(this /* Activity */, this /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        } else {
            return mGoogleApiClient;
        }
    }

    private void handleSignInResult(int requestCode, Intent data, Context context) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            final GoogleSignInAccount acct = result.getSignInAccount();
            //            Log.d("requestCode", "handleSignInResult: " + requestCode);
            //            Log.d("requestCode", "handleSignInResult: " + result.isSuccess());
            if (result.isSuccess()) {
                mTaskGetGG = new TaskGetGG(context) {
                    @Override
                    protected void onPostExecute(String token) {
                        super.onPostExecute(token);
                        mPresenter.loginWithGmail(token, acct.getDisplayName(), "",
                                acct.getEmail());
                        tokenGmail = token;
                        //                        Log.d("acct.getEmail()", "onPostExecute: " + acct.getEmail());
                        //                        Log.d("getDisplayName", "getDisplayName: " + acct.getDisplayName());
                        //                        Log.d("getEmail", "getEmail: " + acct.getEmail());
                    }
                };
                mTaskGetGG.execute(acct.getEmail());
            } else {
                //                Log.d("", "handleSignInResult: " + result.getStatus());
            }
        }
    }

    private void SignOut() {
        GoogleSignIn.getClient(this, gso)
                .signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
