package vn.doithe66.doithe66.presenter;

import vn.doithe66.doithe66.view.LoginActivityView;
import vn.doithe66.doithe66.view.ProgessView;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public class LoginPresenterImpl implements LoginActivityPresenter, OnLoginFinishedListener {
    private ProgessView mProgessView;
    private LoginActivityView mActivityView;
    private LoginInterator mLoginInterator;

    public LoginPresenterImpl(ProgessView progessView, LoginActivityView activityView) {
        mProgessView = progessView;
        mActivityView = activityView;
        mLoginInterator = new LoginIteratorImpl();
    }

    @Override
    public void loginMuatheAccount(String userName, String passWord) {
        mProgessView.showProgess();
        mLoginInterator.login(userName, passWord, this);
    }

    @Override
    public void loginWithGmail(String token, String displayName, String pass, String email) {
        mProgessView.showProgess();
        mLoginInterator.loginGmail(token, displayName, pass, email, this);
    }

    @Override
    public void loginWithFb() {

    }

    @Override
    public void onUsernameError() {
        mActivityView.setUserNameError();
        mProgessView.hideProgess();
    }

    @Override
    public void onPasswordError() {
        mActivityView.setPassWordError();
        mProgessView.hideProgess();
    }

    @Override
    public void onErrorLogin(String error) {
        mActivityView.toastErrorLogin(error);
        mProgessView.hideProgess();
    }

    @Override
    public void onSuccess() {
        mActivityView.navigateToHome();
    }

    @Override
    public void onChooseRepcode(int repCode) {
        mProgessView.hideProgess();
        mActivityView.chooseRepCode(repCode);
    }
}
