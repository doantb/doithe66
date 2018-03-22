package vn.doithe66.doithe66.presenter;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public interface LoginInterator {
    public void login(String userName, String passWord, OnLoginFinishedListener listener);

    public void loginGmail(String token, String displayName, String pass, String email,
            OnLoginFinishedListener listener);
}
