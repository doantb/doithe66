package vn.doithe66.doithe66.presenter;

import android.text.TextUtils;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.RegisterMDResult;
import vn.doithe66.doithe66.model.UserInfo;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public class LoginIteratorImpl implements LoginInterator {

    @Override
    public void login(String userName, String passWord, OnLoginFinishedListener listener) {
        loginAcountMuathe(userName, passWord, listener);
    }

    @Override
    public void loginGmail(String token, String displayName, String pass, String email,
                           OnLoginFinishedListener listener) {
        loginWithGG(token, displayName, pass, email, listener);
    }

    private void loginAcountMuathe(final String email, final String password,
                                   final OnLoginFinishedListener listener) {
        boolean error = false;
        if (TextUtils.isEmpty(email)) {
            listener.onUsernameError();
            error = true;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            error = true;
        }
        if (!error) {
            Retrofit retrofit = ConfigRetrofitApi.getInstance();
            retrofit.create(InterfaceAPI.class)
                    .login(email, password)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.body() != null) {
                                final String token = response.body();
                                if (token != null) {
                                    Log.e("token = ", token);
                                    saveAccessToken(token);
                                    //                                    getUserInfo(token);
                                    Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
                                    retrofit.create(InterfaceAPI.class)
                                            .getUserInfo()
                                            .enqueue(new Callback<UserInfo>() {
                                                @Override
                                                public void onResponse(Call<UserInfo> call,
                                                                       Response<UserInfo> response) {
                                                    if (response.body() != null) {
                                                        UserInfo userInfo = response.body();
                                                        userInfo.setPassWord(password);
                                                        saveUserInfo(userInfo);
                                                        if (response.body().getName() != null) {
                                                            saveEmail(email);
                                                            saveName(response.body().getName());
                                                            listener.onSuccess();
                                                        } else {
                                                            listener.onErrorLogin(token);
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<UserInfo> call,
                                                                      Throwable t) {
                                                }
                                            });
                                }
                            } else {
                                listener.onErrorLogin("Tài khoản hoặc mật khẩu không chính xác");
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            listener.onErrorLogin("Tài khoản hoặc mật khẩu không chính xác");
                        }
                    });
        }
    }

    private void saveUserInfo(UserInfo userInfo) {
        SharedPrefs.getInstance().put(Constant.USER_ACCOUNT, ConfigJson.setUserAccount(userInfo));
    }

    private void loginWithGG(final String token, final String fullName, String passWord,
                             final String email, final OnLoginFinishedListener listener) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance();
        retrofit.create(InterfaceAPI.class)
                .loginWithGoogle(token, fullName, passWord)
                .enqueue(new Callback<RegisterMDResult>() {
                    @Override
                    public void onResponse(Call<RegisterMDResult> call,
                                           Response<RegisterMDResult> response) {
                        if (response.body() != null) {
                            RegisterMDResult mdResult = response.body();
                            saveEmail(email);
                            saveName(fullName);
                            saveAccessToken(mdResult.getToken());
                            if (mdResult.getRepcode() == 1) {
                                Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
                                retrofit.create(InterfaceAPI.class)
                                        .getUserInfo()
                                        .enqueue(new Callback<UserInfo>() {
                                            @Override
                                            public void onResponse(Call<UserInfo> call,
                                                                   Response<UserInfo> response) {
                                                if (response.body() != null) {
                                                    UserInfo userInfo = response.body();
                                                    saveUserInfo(userInfo);
                                                    if (response.body().getName() != null) {
                                                        saveEmail(email);
                                                        saveName(response.body().getName());
                                                        listener.onSuccess();
                                                    } else {
//                                                        listener.onErrorLogin("");
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<UserInfo> call,
                                                                  Throwable t) {
                                            }
                                        });
                            }
                            listener.onChooseRepcode(mdResult.getRepcode());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterMDResult> call, Throwable t) {

                    }
                });
    }

    private void getUserInfo(final String token) {
        if (token != null) {
            Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
            retrofit.create(InterfaceAPI.class).getUserInfo().enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    if (response.body() != null) {
                        if (response.body().getName() == null) {
                            saveName(response.body().getName());
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {
                }
            });
        }
    }

    private void saveAccessToken(String token) {
        SharedPrefs.getInstance().put(Constant.ACCESS_TOKEN, token);
    }

    private void saveEmail(String email) {
        SharedPrefs.getInstance().put(Constant.EMAIL, email);
    }

    private void saveName(String name) {
        SharedPrefs.getInstance().put(Constant.USER_NAME, name);
    }
}
