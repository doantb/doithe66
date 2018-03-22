package vn.doithe66.doithe66.presenter;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public interface OnLoginFinishedListener {
    public void onUsernameError();

    public void onPasswordError();

    public void onErrorLogin(String error);

    public void onSuccess();

    public void onChooseRepcode(int repCode);
}
