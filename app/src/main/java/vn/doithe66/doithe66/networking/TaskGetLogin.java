//package vn.doithe66.doithe66.networking;
//
//import android.content.Context;
//import android.os.AsyncTask;
//
//import vn.doithe66.doithe66.Utils.AutoLogin;
//import vn.doithe66.doithe66.Utils.ConfigJson;
//import vn.doithe66.doithe66.Utils.Constant;
//import vn.doithe66.doithe66.Utils.SharedPrefs;
//import vn.doithe66.doithe66.model.UserInfo;
//
///**
// * Created by Windows 10 Now on 1/18/2018.
// */
//
//public class TaskGetLogin extends AsyncTask<Context, Void, Void> {
//
//    @Override
//    protected Void doInBackground(Context... contexts) {
//        UserInfo mUserInfo = ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class));
//        if (!AutoLogin.bLogin) {
//            AutoLogin autoLogin = new AutoLogin(contexts[0]);
//            autoLogin.login(mUserInfo.getPassWord());
//        }
//        return null;
//    }
//}
