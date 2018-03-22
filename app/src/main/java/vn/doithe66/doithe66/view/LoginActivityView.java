package vn.doithe66.doithe66.view;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public interface LoginActivityView {
    public void setUserNameError();

    public void setPassWordError();

    public void toastErrorLogin(String error);

    public void navigateToHome();

    public void chooseRepCode(int repCode);
}
