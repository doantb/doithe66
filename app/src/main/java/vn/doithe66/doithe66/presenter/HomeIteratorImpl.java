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
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.Amount;
import vn.doithe66.doithe66.model.GetDiscountCard;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.model.UserInfo;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public class HomeIteratorImpl implements HomeIterator {
    @Override
    public void getDataFromToken(String token, ArrayList<ItemCard> itemCards,
            OnHomeGetDiscountFinishedListener listener) {
        itemCards = new ArrayList<>();
        getUserInfo(token, listener);
        getDiscount(token, itemCards, listener);
    }

    private void getUserInfo(String token, final OnHomeGetDiscountFinishedListener listener) {
        if (token != null) {
            Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
            retrofit.create(InterfaceAPI.class).getUserInfo().enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    if (response.body() != null) {
                        if (response.body().getName() == null) {
                            listener.onErrorToken();
                        } else {
                            listener.onSuccessUserInfo(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {
                    listener.onErrorToken();
                }
            });
        }
    }

    private void getDiscount(String token, final ArrayList<ItemCard> itemCards,
            final OnHomeGetDiscountFinishedListener listener) {
        Retrofit retrofit = null;
        if (token == null) {
            retrofit = ConfigRetrofitApi.getInstance();
            listener.onErrorToken();
        } else {
            retrofit = ConfigRetrofitApi.getInstance(token);
        }
        retrofit.create(InterfaceAPI.class)
                .getDiscountCard()
                .enqueue(new Callback<ArrayList<GetDiscountCard>>() {
                    @Override
                    public void onResponse(Call<ArrayList<GetDiscountCard>> call,
                            Response<ArrayList<GetDiscountCard>> response) {
                        if (response.body() != null) {
                            for (GetDiscountCard getDiscountCard : response.body()) {
                                ItemCard itemCard = new ItemCard();
                                itemCard.setProviderCode(getDiscountCard.getProviderCode());
                                itemCard.setProviderId(getDiscountCard.getProviderId());
                                itemCard.setDisCount(getDiscountCard.getPrice());
                                itemCard.setAmounts(
                                        makeDataListPrice(getDiscountCard.getData().toString()));
                                itemCards.add(itemCard);
                            }
                            listener.onSuccess(initData(itemCards));
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<GetDiscountCard>> call, Throwable t) {
                    }
                });
    }

    private ArrayList<Amount> makeDataListPrice(String s) {
        ArrayList<Amount> amounts = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(s);
            ArrayList<String> arrSpin = new ArrayList<String>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                Amount amount = new Amount();
                amount.setProviderId(explrObject.optInt("ProviderId"));
                amount.setCardName(explrObject.optString("CardName"));
                amount.setAmount(explrObject.optString("Amount"));
                arrSpin.add(amount.getCardName());
                amounts.add(amount);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return amounts;
    }

    private ArrayList<ItemCard> initData(ArrayList<ItemCard> mCardArrayList) {
        for (int position = 0; position < mCardArrayList.size(); position++) {
            switch (mCardArrayList.get(position).getProviderId()) {
                case 1:
                    mCardArrayList.get(position).setNameHomeNetWork("Viettel");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_viettel);
                    break;
                case 2:
                    mCardArrayList.get(position).setNameHomeNetWork("Mobifone");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_mobifone);
                    break;
                case 3:
                    mCardArrayList.get(position).setNameHomeNetWork("VinaPhone");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_vinaphone);
                    break;
                case 5:
                    mCardArrayList.get(position).setNameHomeNetWork("Gate");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_fptgate);
                    break;
                case 7:
                    mCardArrayList.get(position).setNameHomeNetWork("VietnamMobile");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_vietnammobile);
                    break;
                case 12:
                    mCardArrayList.get(position).setNameHomeNetWork("Zing");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_zing);
                    break;
                case 13:
                    mCardArrayList.get(position).setNameHomeNetWork("VCoin");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_vcoin);
                    break;
                case 14:
                    mCardArrayList.get(position).setNameHomeNetWork("Garena");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_garena);
                    break;
                case 17:
                    mCardArrayList.get(position).setNameHomeNetWork("Bit");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_bit);
                    break;
                case 22:
                    mCardArrayList.get(position).setNameHomeNetWork("Mobi data");
                    mCardArrayList.get(position).setImgLogo(R.drawable.logo_mobidata);
                    break;
                case 23:
                    mCardArrayList.get(position).setNameHomeNetWork("Bitdefender");
                    mCardArrayList.get(position).setImgLogo(R.drawable.logo_bitdefender_red);
                    break;
                case 24:
                    mCardArrayList.get(position).setNameHomeNetWork("KPS Anti");
                    mCardArrayList.get(position).setImgLogo(R.drawable.logo_kapersky_antivirut);
                    break;
                case 25:
                    mCardArrayList.get(position).setNameHomeNetWork("KPS Internet");
                    mCardArrayList.get(position).setImgLogo(R.drawable.kaspersky_logo);
                    break;
            }
        }
        return mCardArrayList;
    }
}
