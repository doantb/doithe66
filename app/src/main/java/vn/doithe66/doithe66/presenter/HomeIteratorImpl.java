package vn.doithe66.doithe66.presenter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
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
        itemCards = (ArrayList<ItemCard>) ConfigJson.getListTypeCard(SharedPrefs.getInstance().get(Constant.LIST_ITEM_CARD, String.class));
        if (itemCards != null) {
            listener.onSuccess(itemCards);
        } else {
            itemCards = new ArrayList<>();
            getUserInfo(token, listener, itemCards);
        }
    }

    private void getUserInfo(final String token, final OnHomeGetDiscountFinishedListener listener, final ArrayList<ItemCard> itemCards) {
        if (token != null) {
            Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
            retrofit.create(InterfaceAPI.class).getUserInfo().enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    if (response.body() != null) {
                        if (response.body().getName() == null) {
                            loginReset(listener, itemCards);
                            listener.onErrorToken();
                        } else {
                            getDiscount(token, itemCards, listener);
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

    private void loginReset(final OnHomeGetDiscountFinishedListener listener, final ArrayList<ItemCard> itemCards) {
        if (ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class)) != null) {
            UserInfo userInfo = ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class));
            Retrofit retrofit = ConfigRetrofitApi.getInstance();
            retrofit.create(InterfaceAPI.class).login(userInfo.getUserName(), userInfo.getPassWord()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    final String token = response.body();
                    if (token != null) {
                        saveAccessToken(token);
                        getUserInfo(token, listener, itemCards);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

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
                                String number = new DecimalFormat("##.##").format(100.00 - getDiscountCard.Price);
                                NumberFormat nf = NumberFormat.getInstance();
                                try {
                                    double discount = nf.parse(number).doubleValue();
                                    itemCard.setDisCountDouble(discount);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                itemCard.setProviderCode(getDiscountCard.getProviderCode());
                                itemCard.setProviderId(getDiscountCard.getProviderId());
                                itemCard.setDisCount(getDiscountCard.getPrice());
                                itemCard.setmAmounts(makeDataListItemPrice(getDiscountCard.getData()));
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

    private ArrayList<Amount> makeDataListItemPrice(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (data != null) {
                List<Amount> amountList = mapper.readValue(data, new TypeReference<List<Amount>>() {
                });
                return (ArrayList<Amount>) amountList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveListItemCard(ArrayList<ItemCard> itemCards) {
        SharedPrefs.getInstance().put(Constant.LIST_ITEM_CARD, ConfigJson.setListTypeCard(itemCards));
    }

//    private ArrayList<Amount> makeDataListPrice(String s) {
//        ArrayList<Amount> amounts = new ArrayList<>();
//        try {
//            JSONArray jsonArray = new JSONArray(s);
//            ArrayList<String> arrSpin = new ArrayList<String>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject explrObject = jsonArray.getJSONObject(i);
//                Amount amount = new Amount();
//                amount.setProviderId(explrObject.optInt("ProviderId"));
//                amount.setCardName(explrObject.optString("CardName"));
//                amount.setAmount(explrObject.optString("Amount"));
//                arrSpin.add(amount.getCardName());
//                amounts.add(amount);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return amounts;
//    }

    private ArrayList<ItemCard> initData(ArrayList<ItemCard> mCardArrayList) {
        ArrayList<ItemCard> itemCards = new ArrayList<>();
        for (int position = 0; position < mCardArrayList.size(); position++) {
            switch (mCardArrayList.get(position).getProviderId()) {
                case 1:
                    mCardArrayList.get(position).setNameHomeNetWork("Viettel");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_viettel);
                    itemCards.add(mCardArrayList.get(position));
                    break;
                case 2:
                    mCardArrayList.get(position).setNameHomeNetWork("Mobifone");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_mobifone);
                    itemCards.add(mCardArrayList.get(position));
                    break;
                case 3:
                    mCardArrayList.get(position).setNameHomeNetWork("VinaPhone");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_vinaphone);
                    itemCards.add(mCardArrayList.get(position));
                    break;
                case 5:
                    mCardArrayList.get(position).setNameHomeNetWork("Gate");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_fptgate);
                    itemCards.add(mCardArrayList.get(position));
                    break;
                case 7:
                    mCardArrayList.get(position).setNameHomeNetWork("VietnamMobile");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_vietnammobile);
                    itemCards.add(mCardArrayList.get(position));
                    break;
//                case 8:
//                    mCardArrayList.get(position).setNameHomeNetWork("OnGame");
//                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_vietnammobile);
//                    break;
                case 12:
                    mCardArrayList.get(position).setNameHomeNetWork("Zing");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_zing);
                    itemCards.add(mCardArrayList.get(position));
                    break;
                case 13:
                    mCardArrayList.get(position).setNameHomeNetWork("VCoin");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_vcoin);
                    itemCards.add(mCardArrayList.get(position));
                    break;
                case 14:
                    mCardArrayList.get(position).setNameHomeNetWork("Garena");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_garena);
                    itemCards.add(mCardArrayList.get(position));
                    break;
                case 17:
                    mCardArrayList.get(position).setNameHomeNetWork("Bit");
                    mCardArrayList.get(position).setImgLogo(R.drawable.ic_bit);
                    itemCards.add(mCardArrayList.get(position));
                    break;
                case 16:
                    mCardArrayList.get(position).setNameHomeNetWork("Mobi data");
                    mCardArrayList.get(position).setImgLogo(R.drawable.logo_mobidata);
                    itemCards.add(mCardArrayList.get(position));
                    break;
//                case 23:
//                    mCardArrayList.get(position).setNameHomeNetWork("Bitdefender");
//                    mCardArrayList.get(position).setImgLogo(R.drawable.logo_bitdefender_red);
//                    break;
//                case 24:
//                    mCardArrayList.get(position).setNameHomeNetWork("KPS Anti");
//                    mCardArrayList.get(position).setImgLogo(R.drawable.logo_kapersky_antivirut);
//                    break;
//                case 25:
//                    mCardArrayList.get(position).setNameHomeNetWork("KPS Internet");
//                    mCardArrayList.get(position).setImgLogo(R.drawable.kaspersky_logo);
//                    break;
            }
        }
        saveListItemCard(itemCards);
        return itemCards;
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
