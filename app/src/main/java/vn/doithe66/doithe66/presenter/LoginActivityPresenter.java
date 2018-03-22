package vn.doithe66.doithe66.presenter;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public interface LoginActivityPresenter {
    public void loginMuatheAccount(String userName,String passWord);

    public void loginWithGmail(String token, String displayName, String s, String email);

    public void loginWithFb();
}
