package vn.doithe66.doithe66.presenter;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.DataResultHistory;
import vn.doithe66.doithe66.model.HistoryDetail;
import vn.doithe66.doithe66.model.ResultHistory;
import vn.doithe66.doithe66.view.HistoryFrmView;

import static vn.doithe66.doithe66.Utils.Constant.PAY_CARD_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.PAY_FAST_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.RECEIVER_MONEY;
import static vn.doithe66.doithe66.Utils.Constant.TAKE_MONEY;
import static vn.doithe66.doithe66.Utils.Constant.TRANSFER_MONEY;

/**
 * Created by Windows 10 Now on 1/20/2018.
 */

public class HistoryInteratorImpl implements HistoryIterator {
    private HistoryFrmView mHistoryFrmView;

    @Override
    public void initDataForListHistory(ArrayList<HistoryDetail> historyDetails) {
        historyDetails.addAll(initDataForHistory(historyDetails));
    }

    @Override
    public void getDataForHistoryDetail(ArrayList<DataResultHistory> dataResultHistory,
                                        int position, String frDate, String toDate, String sFoneItemHistory, String sPrice,
                                        String token, OnSeeHistoryFinishListener onSeeHistoryFinishListener) {
        switch (position) {
            case PAY_CARD_FRAGMENT:
                getHistoryBuyCard(dataResultHistory, frDate, toDate, sFoneItemHistory, sPrice, token,
                        onSeeHistoryFinishListener);
                break;
            case PAY_FAST_FRAGMENT:
                getHistoryLoadMoneyFoneFast(dataResultHistory, frDate, toDate, sFoneItemHistory, sPrice, token,
                        onSeeHistoryFinishListener);
                break;
            case TRANSFER_MONEY:
                getHistoryTransferMoney(dataResultHistory, token, frDate, toDate, onSeeHistoryFinishListener);
                break;
            case TAKE_MONEY:
                getHistoryTakeMoney(dataResultHistory, frDate, toDate, sFoneItemHistory, sPrice,
                        token, onSeeHistoryFinishListener);
                break;
            case RECEIVER_MONEY:
                getHistoryReceiverMoney(dataResultHistory, frDate, toDate, token, onSeeHistoryFinishListener);
                break;

        }
    }

    private void getHistoryReceiverMoney(final ArrayList<DataResultHistory> dataResultHistory, String frDate, String toDate, String token, final OnSeeHistoryFinishListener onSeeHistoryFinishListener) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
        retrofit.create(InterfaceAPI.class)
                .historyReceiver(frDate, toDate, 1)
                .enqueue(new Callback<ResultHistory>() {
                    @Override
                    public void onResponse(Call<ResultHistory> call,
                                           Response<ResultHistory> response) {

                        String string = "" + response.body().getData();
                        String sMesagge = response.body().getMessage();
                        getDataHistory(dataResultHistory, string);
                        onSeeHistoryFinishListener.onSuccess(sMesagge);
                    }

                    @Override
                    public void onFailure(Call<ResultHistory> call, Throwable t) {

                    }
                });
    }

    private void getHistoryTransferMoney(final ArrayList<DataResultHistory> dataResultHistory, String token, String frDate, String toDate, final OnSeeHistoryFinishListener onSeeHistoryFinishListener) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
        retrofit.create(InterfaceAPI.class)
                .historyTransfer(frDate, toDate, 1)
                .enqueue(new Callback<ResultHistory>() {
                    @Override
                    public void onResponse(Call<ResultHistory> call,
                                           Response<ResultHistory> response) {

                        String string = "" + response.body().getData();
                        String sMesagge = response.body().getMessage();
                        getDataHistory(dataResultHistory, string);
                        onSeeHistoryFinishListener.onSuccess(sMesagge);
                    }

                    @Override
                    public void onFailure(Call<ResultHistory> call, Throwable t) {

                    }
                });

    }

    private ArrayList<HistoryDetail> initDataForHistory(ArrayList<HistoryDetail> historyDetails) {
        historyDetails = new ArrayList<>();
//        historyDetails.add(
//                new HistoryDetail(Constant.historyPayAccount, R.drawable.ic_account_100px,
//                        R.color.teal_200));
        historyDetails.add(
                new HistoryDetail(Constant.historyPayCodeCard, R.drawable.ic_card_history_100px,
                        R.color.teal_400));
        historyDetails.add(
                new HistoryDetail(Constant.historyPayPhoneFast, R.drawable.ic_history_fast_100px,
                        R.color.teal_600));
        historyDetails.add(
                new HistoryDetail(Constant.historyTransferMoney, R.drawable.ic_history_slow_100px,
                        R.color.teal_800));
        historyDetails.add(
                new HistoryDetail(Constant.historyReceiverMoney, R.drawable.gift_card_100px,
                        R.color.teal_900));
        historyDetails.add(
                new HistoryDetail(Constant.historyMakeMoney, R.drawable.card_payment_100px,
                        R.color.teal_1000));
        return historyDetails;
    }

    public void getDatahistoryPayAccount(final ArrayList<DataResultHistory> dataResultHistory,
                                         String frDate, String toDate, String token,
                                         final OnSeeHistoryFinishListener onSeeHistoryFinishListener) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
        retrofit.create(InterfaceAPI.class)
                .historyTransfer(frDate, toDate, 1)
                .enqueue(new Callback<ResultHistory>() {
                    @Override
                    public void onResponse(Call<ResultHistory> call,
                                           Response<ResultHistory> response) {

                        String string = "" + response.body().getData();
                        String sMesagge = response.body().getMessage();
                        getDataHistory(dataResultHistory, string);
                        onSeeHistoryFinishListener.onSuccess(sMesagge);
                    }

                    @Override
                    public void onFailure(Call<ResultHistory> call, Throwable t) {

                    }
                });
    }

    // ham lay api lich su nap tien dien thoai nhanh:
    public void getHistoryLoadMoneyFoneFast(final ArrayList<DataResultHistory> dataResultHistory,
                                            String frDate, String toDate, String sItem,
                                            String sPrice, String token, final OnSeeHistoryFinishListener onSeeHistoryFinishListener) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
        retrofit.create(InterfaceAPI.class)
                .getHistoryPayPhone(frDate, toDate, sItem, sPrice, 1)
                .enqueue(new Callback<ResultHistory>() {
                    @Override
                    public void onResponse(Call<ResultHistory> call,
                                           Response<ResultHistory> response) {

                        String string = "" + response.body().getData();
                        String sMesagge = response.body().getMessage();
                        getDataHistory(dataResultHistory, string);
                        onSeeHistoryFinishListener.onSuccess(sMesagge);
                    }

                    @Override
                    public void onFailure(Call<ResultHistory> call, Throwable t) {

                    }
                });
    }

    // ham lay api lich su nap tien dien thoai cham:
    public void getHistoryTakeMoney(final ArrayList<DataResultHistory> dataResultHistory,
                                    String frDate, String toDate, String sFoneLoadMoneySlow, String sPrice, String token,
                                    final OnSeeHistoryFinishListener onSeeHistoryFinishListener) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
        retrofit.create(InterfaceAPI.class)
                .getHistoryMakeMoney(frDate, toDate, sFoneLoadMoneySlow, sPrice, 1)
                .enqueue(new Callback<ResultHistory>() {
                    @Override
                    public void onResponse(Call<ResultHistory> call,
                                           Response<ResultHistory> response) {

                        String string = "" + response.body().getData();
                        String sMesagge = response.body().getMessage();
                        getDataHistory(dataResultHistory, string);
                        onSeeHistoryFinishListener.onSuccess(sMesagge);
                    }

                    @Override
                    public void onFailure(Call<ResultHistory> call, Throwable t) {

                    }
                });
    }

    // ham lay api lich su mua ma the:
    public void getHistoryBuyCard(final ArrayList<DataResultHistory> dataResultHistory,
                                  String fromDate, String toDate, String edtItem, String menhGia, String token,
                                  final OnSeeHistoryFinishListener onSeeHistoryFinishListener) {
        Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
        retrofit.create(InterfaceAPI.class)
                .getHistoryBuyCard(fromDate, toDate, edtItem, menhGia, 1)
                .enqueue(new Callback<ResultHistory>() {
                    @Override
                    public void onResponse(Call<ResultHistory> call,
                                           Response<ResultHistory> response) {

                        String string = "" + response.body().getData();
                        String sMesagge = response.body().getMessage();
                        getDataHistory(dataResultHistory, string);
                        onSeeHistoryFinishListener.onSuccess(sMesagge);
                    }

                    @Override
                    public void onFailure(Call<ResultHistory> call, Throwable t) {

                    }
                });
    }

    private void getDataHistory(ArrayList<DataResultHistory> dataResultHistorys,
                                String string) {
        DataResultHistory mDataResultHistory = null;
        try {
            JSONArray jsonArray = new JSONArray(string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                mDataResultHistory = new DataResultHistory();
                mDataResultHistory.setType(jsonObject.optString("Type"));
                mDataResultHistory.setEmail(jsonObject.optString("Email"));
                mDataResultHistory.setStrPrice(jsonObject.optString("StrPrice"));
                mDataResultHistory.setStrAmount(jsonObject.optString("StrAmount"));
                mDataResultHistory.setCreateDate(jsonObject.optString("CreateDate"));
                mDataResultHistory.setStatus(jsonObject.optString("Status"));
                mDataResultHistory.setPhone(jsonObject.optString("Phone"));
                mDataResultHistory.setDiscount(jsonObject.optString("ChietKhau"));
                mDataResultHistory.setTelcoCode(jsonObject.optString("TelcoCode"));
                dataResultHistorys.add(mDataResultHistory);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
