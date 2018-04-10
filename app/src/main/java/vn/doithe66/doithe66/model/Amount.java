package vn.doithe66.doithe66.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 Now on 1/10/2018.
 */

public class Amount implements Parcelable {
    //    @SerializedName("ProviderId")//RepCode là thuộc tính trong json được trả về
    public int ProviderId;//RepCode này là thuộc tính của APIResult,
    //khai bao như này là để nó map RepCode của json vào RepCode của object

    //    @SerializedName("CardName")
    public String CardName;

    //    @SerializedName("Amount")
    public String Amount;

    public String Id;

    public String ImageUrl;
    private boolean isWatch = false;

    public Amount() {
    }

    public boolean isWatch() {
        return isWatch;
    }

    public void setWatch(boolean watch) {
        isWatch = watch;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ProviderId);
        dest.writeString(this.CardName);
        dest.writeString(this.Amount);
    }

    protected Amount(Parcel in) {
        this.ProviderId = in.readInt();
        this.CardName = in.readString();
        this.Amount = in.readString();
    }

    public static final Parcelable.Creator<Amount> CREATOR = new Parcelable.Creator<Amount>() {
        @Override
        public Amount createFromParcel(Parcel source) {
            return new Amount(source);
        }

        @Override
        public Amount[] newArray(int size) {
            return new Amount[size];
        }
    };
}