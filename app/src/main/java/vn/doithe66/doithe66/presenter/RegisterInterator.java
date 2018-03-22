package vn.doithe66.doithe66.presenter;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public interface RegisterInterator {
    public void register(String user, String phone, String email, String pass,OnRegisterFinishedListener listener);
}
