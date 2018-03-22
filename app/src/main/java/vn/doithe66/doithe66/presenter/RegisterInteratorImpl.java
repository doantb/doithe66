package vn.doithe66.doithe66.presenter;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.RegisterMDResult;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public class RegisterInteratorImpl implements RegisterInterator {

    @Override
    public void register(String user, String phone, String email, String pass,
            OnRegisterFinishedListener listener) {
        registerRetrofit(user, phone, email, pass, listener);
    }

    private void registerRetrofit(String user, String phone, String email, String pass,
            final OnRegisterFinishedListener listener) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance();
        retrofit.create(InterfaceAPI.class)
                .register(user, phone, email, pass)
                .enqueue(new Callback<RegisterMDResult>() {
                    @Override
                    public void onResponse(Call<RegisterMDResult> call,
                            Response<RegisterMDResult> response) {
                        Log.e("RegisterMDResult ", response.body().toString());
                        if (response.body() != null) {
                            listener.onSuccess(response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterMDResult> call, Throwable t) {

                    }
                });
    }
}
