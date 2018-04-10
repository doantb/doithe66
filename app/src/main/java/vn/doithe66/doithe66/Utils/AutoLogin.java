package vn.doithe66.doithe66.Utils;

import android.content.Context;
import android.content.Intent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.LoginActivity;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.UserInfo;

public  class AutoLogin {
    private String token;
    public  boolean bLogin = false;
    private Context context;

    public AutoLogin(Context context) {
        token = SharedPrefs.getInstance().get(Constant.ACCESS_TOKEN, String.class);
        this.context = context;
    }

    public void login(final String passWord) {
        if (token != null) {
            Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
            retrofit.create(InterfaceAPI.class).getUserInfo().enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    if (response.body() != null) {
                        if (response.body().getName() == null) {
                            loginAgain();
                            bLogin = false;
                        } else {
                            bLogin = true;
                            response.body().setPassWord(passWord);
                            saveUserInfo(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {

                }
            });
        }
    }

    private void loginAgain() {
        if (ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class)) != null) {
            final UserInfo userInfo = ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class));
            Retrofit retrofit = ConfigRetrofitApi.getInstance();
            retrofit.create(InterfaceAPI.class).login(userInfo.getUserName(), userInfo.getPassWord()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    token = response.body();
                    if (token != null) {
                        saveAccessToken(token);
                        login(userInfo.getPassWord());
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        } else {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }

    private void saveAccessToken(String token) {
        SharedPrefs.getInstance().put(Constant.ACCESS_TOKEN, token);
    }

    private void saveUserInfo(UserInfo userInfo) {
        SharedPrefs.getInstance().put(Constant.USER_ACCOUNT, ConfigJson.setUserAccount(userInfo));
    }
}
