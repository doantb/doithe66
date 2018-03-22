package vn.doithe66.doithe66.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 Now on 1/10/2018.
 */

public class Amount {
    @SerializedName("ProviderId")//RepCode là thuộc tính trong json được trả về
    public int ProviderId;//RepCode này là thuộc tính của APIResult,
    //khai bao như này là để nó map RepCode của json vào RepCode của object

    @SerializedName("CardName")
    public String CardName;

    @SerializedName("Amount")
    public String Amount;

    @SerializedName("Data")
    public Object Data;

    public Amount(int providerId, String cardName, String amount, Object data) {
        ProviderId = providerId;
        CardName = cardName;
        Amount = amount;
        Data = data;
    }

    public Amount() {
    }

    public int getProviderId() {
        return ProviderId;
    }

    public void setProviderId(int providerId) {
        ProviderId = providerId;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}