package vn.doithe66.doithe66.presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.RegisterMDResult;

public class InfoBankFrmIteratorImpl implements InfoBankFrmIterator {
    @Override
    public void editAccountBank(String token, String fname, String phone, String nameBank, String ownBank, String branchBank, String numberBank, OnHandlleChangeAccountBankListener onHandlleChangeAccountBankListener) {
        editAccountWithRetrofit(token,fname, phone, nameBank, ownBank, branchBank,numberBank, onHandlleChangeAccountBankListener);
    }

    private void editAccountWithRetrofit(String token, String fname, String phone, String nameBank, String ownBank, String branchBank, String numberBank, final OnHandlleChangeAccountBankListener onHandlleChangeAccountBankListener) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
        retrofit.create(InterfaceAPI.class).updateAccountBank(fname,phone,numberBank,ownBank,nameBank,branchBank).enqueue(new Callback<RegisterMDResult>() {
            @Override
            public void onResponse(Call<RegisterMDResult> call, Response<RegisterMDResult> response) {
                onHandlleChangeAccountBankListener.onSuccessEdit(response.body().Message);
            }

            @Override
            public void onFailure(Call<RegisterMDResult> call, Throwable t) {

            }
        });
    }
}
