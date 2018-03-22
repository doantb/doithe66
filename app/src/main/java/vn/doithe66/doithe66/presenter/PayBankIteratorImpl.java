package vn.doithe66.doithe66.presenter;

import Decoder.BASE64Decoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.InfoUserEdit;
import vn.doithe66.doithe66.model.RegisterMDResult;
import vn.doithe66.doithe66.model.ResultBanThe;

import static vn.doithe66.doithe66.Utils.Constant.PAY_CARD_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.PAY_FAST_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.PAY_SLOW_FRAGMENT;

/**
 * Created by Windows 10 Now on 1/25/2018.
 */

public class PayBankIteratorImpl implements PayBankIterator {
    Retrofit retrofit;
    InfoUserEdit mUserEdit;
    String url;
    String token;

    @Override
    public void getLinkPayBank(int positionFragment, String token, InfoUserEdit infoUserEdit,
            String url, OnGetUrlFinishedListener onGetUrlFinishedListener) {
        this.mUserEdit = infoUserEdit;
        this.token = token;
        this.url = url;
        switch (positionFragment) {
            case PAY_CARD_FRAGMENT:
                getLinkApiByBank(onGetUrlFinishedListener);
                break;
            case PAY_FAST_FRAGMENT:
                getLinkApiFast(onGetUrlFinishedListener);
                break;
            case PAY_SLOW_FRAGMENT:
                getLinkApiSlow(onGetUrlFinishedListener);
                break;
        }
    }

    @Override
    public void getListCard(int positionFragment, String token, InfoUserEdit infoUserEdit,
            OnGetListCardFinishedListener onGetListCardFinishedListener) {
        this.mUserEdit = infoUserEdit;
        this.token = token;
        this.url = url;
        switch (positionFragment) {
            case PAY_CARD_FRAGMENT:
                getLinkApiByBankCard(onGetListCardFinishedListener);
                break;
            case PAY_FAST_FRAGMENT:
                getLinkApiFastBanThe(onGetListCardFinishedListener);
                break;
            case PAY_SLOW_FRAGMENT:
                getLinkApiSlowBanThe(onGetListCardFinishedListener);
                break;
        }
    }

    private void getLinkApiByBank(final OnGetUrlFinishedListener onGetUrlFinishedListener) {
        if (token.isEmpty()) {
            retrofit = ConfigRetrofitApi.getInstance();
        } else {
            retrofit = ConfigRetrofitApi.getInstance(token);
        }
        retrofit.create(InterfaceAPI.class)
                .payCardByBank(mUserEdit.getBankCode(), mUserEdit.getEmail(),
                        mUserEdit.getProviderId(), stringToInt(mUserEdit.getPrice()),
                        String.valueOf(mUserEdit.getCountBuy()))
                .enqueue(new Callback<RegisterMDResult>() {
                    @Override
                    public void onResponse(Call<RegisterMDResult> call,
                            Response<RegisterMDResult> response) {
                        if (response != null) {
                            onGetUrlFinishedListener.onSuccess(response.body().getLink());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterMDResult> call, Throwable t) {
                        onGetUrlFinishedListener.onError();
                    }
                });
    }

    private void getLinkApiFast(final OnGetUrlFinishedListener onGetUrlFinishedListener) {
        if (token.isEmpty()) {
            retrofit = ConfigRetrofitApi.getInstance();
        } else {
            retrofit = ConfigRetrofitApi.getInstance(token);
        }
        retrofit.create(InterfaceAPI.class)
                .payCardFast(mUserEdit.getBankCode(), mUserEdit.getNumberPhone(),
                        stringToInt(mUserEdit.getPrice()), mUserEdit.getEmail(),
                        mUserEdit.getTypeOfPay())
                .enqueue(new Callback<RegisterMDResult>() {
                    @Override
                    public void onResponse(Call<RegisterMDResult> call,
                            Response<RegisterMDResult> response) {
                        if (response != null) {
                            onGetUrlFinishedListener.onSuccess(response.body().getLink());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterMDResult> call, Throwable t) {
                        onGetUrlFinishedListener.onError();
                    }
                });
    }

    private void getLinkApiSlow(final OnGetUrlFinishedListener onGetUrlFinishedListener) {
        if (token.isEmpty()) {
            retrofit = ConfigRetrofitApi.getInstance();
        } else {
            retrofit = ConfigRetrofitApi.getInstance(token);
        }
        retrofit.create(InterfaceAPI.class)
                .payCardViettelSlow(mUserEdit.getBankCode(), mUserEdit.getNumberPhone(),
                        stringToInt(mUserEdit.getPrice()), mUserEdit.getEmail(),
                        mUserEdit.getTypeOfPay())
                .enqueue(new Callback<RegisterMDResult>() {
                    @Override
                    public void onResponse(Call<RegisterMDResult> call,
                            Response<RegisterMDResult> response) {
                        if (response != null) {
                            onGetUrlFinishedListener.onSuccess(response.body().getLink());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterMDResult> call, Throwable t) {
                        onGetUrlFinishedListener.onError();
                    }
                });
    }

    private void getLinkApiByBankCard(
            final OnGetListCardFinishedListener onGetListCardFinishedListener) {
        if (token.isEmpty()) {
            onGetListCardFinishedListener.onErrorGetListCard();
        } else {
            retrofit = ConfigRetrofitApi.getInstance(token);
            retrofit.create(InterfaceAPI.class)
                    .payCardByBankBanThe(mUserEdit.getProviderCode()
                            + ":"
                            + stringToInt(mUserEdit.getPrice())
                            + ":"
                            + String.valueOf(mUserEdit.getCountBuy()))
                    .enqueue(new Callback<ResultBanThe>() {
                        @Override
                        public void onResponse(Call<ResultBanThe> call,
                                Response<ResultBanThe> response) {
                            if (response != null) {
                                ResultBanThe mResultBanThe = response.body();
                                if (mResultBanThe != null) {
//                                    Log.e("card", response.body().getLinlistCardsk());
//                                    Log.e("card", mResultBanThe.getMessage());
//                                    Log.e("card",
//                                            String.valueOf(mResultBanThe.getErrorCode()));
                                    // lay ra listcard tu Api tra ve de giai ma :
                                    String sListCards = response.body().getLinlistCardsk();
                                    if (sListCards != null
                                            && !sListCards.isEmpty()
                                            && !sListCards.equalsIgnoreCase("")) {
                                        try {
                                            sListCards = Decrypt(token, sListCards);
//                                            Log.e("card", sListCards);
//                                            Log.e("card", mResultBanThe.getMessage());
//                                            Log.e("card",
//                                                    String.valueOf(mResultBanThe.getErrorCode()));
                                            onGetListCardFinishedListener.onGetListCardSuccess(
                                                    sListCards, mResultBanThe.getMessage());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        onGetListCardFinishedListener.onGetListCardSuccess(
                                                sListCards, mResultBanThe.getMessage());
                                    }
                                } else {
                                    onGetListCardFinishedListener.onErrorGetListCard();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultBanThe> call, Throwable t) {
                            onGetListCardFinishedListener.onErrorGetListCard();
                        }
                    });
        }
    }

    private void getLinkApiFastBanThe(
            final OnGetListCardFinishedListener onGetListCardFinishedListener) {
        if (token.isEmpty()) {
            onGetListCardFinishedListener.onErrorGetListCard();
        } else {
            retrofit = ConfigRetrofitApi.getInstance(token);
            retrofit.create(InterfaceAPI.class)
                    .payCardFastBanThe(mUserEdit.getNumberPhone()
                            + ":"
                            + stringToInt(mUserEdit.getPrice())
                            + ":"
                            + mUserEdit.getTypeOfPay())
                    .enqueue(new Callback<ResultBanThe>() {
                        @Override
                        public void onResponse(Call<ResultBanThe> call,
                                Response<ResultBanThe> response) {
                            if (response.body() != null) {
//                                Log.e("card", response.body().getLinlistCardsk());
//                                Log.e("card", response.body().getMessage());
//                                Log.e("card",
//                                        String.valueOf(response.body().getErrorCode()));
                                ResultBanThe mResultBanThe = response.body();
                                if (mResultBanThe.getMessage() != null) {
                                    onGetListCardFinishedListener.onGetListCardSuccess("",
                                            mResultBanThe.getMessage());
                                } else {
                                    onGetListCardFinishedListener.onErrorGetListCard();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultBanThe> call, Throwable t) {
                            onGetListCardFinishedListener.onErrorGetListCard();
                        }
                    });
        }
    }

    private void getLinkApiSlowBanThe(
            final OnGetListCardFinishedListener onGetListCardFinishedListener) {

        if (token.isEmpty()) {
            onGetListCardFinishedListener.onErrorGetListCard();
        } else {
            retrofit = ConfigRetrofitApi.getInstance(token);
            retrofit.create(InterfaceAPI.class)
                    .payCardViettelSlowBanThe(mUserEdit.getNumberPhone()
                            + ":"
                            + stringToInt(mUserEdit.getPrice())
                            + ":"
                            + mUserEdit.getTypeOfPay())
                    .enqueue(new Callback<ResultBanThe>() {
                        @Override
                        public void onResponse(Call<ResultBanThe> call,
                                Response<ResultBanThe> response) {
                            if (response.body() != null) {
                                ResultBanThe mResultBanThe = response.body();
                                if (mResultBanThe.getMessage() != null) {
                                    onGetListCardFinishedListener.onGetListCardSuccess("",
                                            mResultBanThe.getMessage());
                                } else {
                                    onGetListCardFinishedListener.onErrorGetListCard();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultBanThe> call, Throwable t) {
                            onGetListCardFinishedListener.onErrorGetListCard();
                        }
                    });
        }
    }

    public static int stringToInt(String s) {
        String newS = s.replace(".", "");
        String newS1 = newS.replace(" ", "");
        String newS2 = newS1.substring(0, newS1.length() - 1);
        return Integer.parseInt(newS2);
    }

    //    key là token value(data) là listcarrd
    public static String Decrypt(String key, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS7Padding");
        String keymd5 = getMD5(key).substring(0, 24);
        SecretKeySpec keyspec = new SecretKeySpec(keymd5.getBytes(), "TripleDES");
        cipher.init(Cipher.DECRYPT_MODE, keyspec);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] raw = decoder.decodeBuffer(data);
        byte[] stringBytes = cipher.doFinal(raw);
        String result = new String(stringBytes);
        return result;
    }

    public static String getMD5(String sMessage) {
        byte[] defaultBytes = sMessage.getBytes();
        try {

            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException nsae)

        {
            return null;
        }
    }
}
