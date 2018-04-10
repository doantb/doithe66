package vn.doithe66.doithe66.presenter;

import vn.doithe66.doithe66.view.ProgessView;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public class RegisterPresenterImpl implements RegisterPresenter, OnRegisterFinishedListener {
    private ProgessView mProgessView;
    private RegisterView mRegisterView;
    private RegisterInterator mInterator;

    public RegisterPresenterImpl(ProgessView progessView, RegisterView registerView) {
        mProgessView = progessView;
        mRegisterView = registerView;
        mInterator = new RegisterInteratorImpl();
    }

    @Override
    public void onEmailError() {

    }

    @Override
    public void onSuccess(String sMessage) {
        mProgessView.hideProgess();
        mRegisterView.navigationToLogin(sMessage);
    }

    @Override
    public void register(String user, String phone, String email, String pass, String passlv2) {
        mInterator.register(user, phone, email, pass, passlv2, this);
        mProgessView.showProgess();
    }
}
