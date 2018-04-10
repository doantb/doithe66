package vn.doithe66.doithe66.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Windows 10 Now on 1/10/2018.
 */

public class ItemCard implements Parcelable {
    private String nameHomeNetWork;
    private int imgLogo;
    private boolean isWatch = false;
    private String disCount;
    private int providerId;
    private String providerCode;
    private ArrayList<Amount> mAmounts;
    private ArrayList<String> arrSpin;
    private double disCountDouble;

    public ItemCard(String nameHomeNetWork, int imgLogo) {
        this.nameHomeNetWork = nameHomeNetWork;
        this.imgLogo = imgLogo;
    }

    public ItemCard(String nameHomeNetWork, int imgLogo, String disCount) {
        this.nameHomeNetWork = nameHomeNetWork;
        this.imgLogo = imgLogo;
        this.disCount = disCount;
    }

    public ItemCard(String nameHomeNetWork, int imgLogo, boolean isWatch, String disCount) {
        this.nameHomeNetWork = nameHomeNetWork;
        this.imgLogo = imgLogo;
        this.isWatch = isWatch;
        this.disCount = disCount;
    }

    public ItemCard() {

    }

    public String getNameHomeNetWork() {
        return nameHomeNetWork;
    }

    public void setNameHomeNetWork(String nameHomeNetWork) {
        this.nameHomeNetWork = nameHomeNetWork;
    }

    public double getDisCountDouble() {
        return disCountDouble;
    }

    public void setDisCountDouble(double disCountDouble) {
        this.disCountDouble = disCountDouble;
    }

    public int getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(int imgLogo) {
        this.imgLogo = imgLogo;
    }

    public boolean isWatch() {
        return isWatch;
    }

    public void setWatch(boolean watch) {
        isWatch = watch;
    }

    public String getDisCount() {
        return disCount;
    }

    public void setDisCount(String disCount) {
        this.disCount = disCount;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public ArrayList<Amount> getmAmounts() {
        return mAmounts;
    }

    public void setmAmounts(ArrayList<Amount> mAmounts) {
        this.mAmounts = mAmounts;
    }

    public ArrayList<String> getArrSpin() {
        return arrSpin;
    }

    public void setArrSpin(ArrayList<String> arrSpin) {
        this.arrSpin = arrSpin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nameHomeNetWork);
        dest.writeInt(this.imgLogo);
        dest.writeByte(this.isWatch ? (byte) 1 : (byte) 0);
        dest.writeString(this.disCount);
        dest.writeInt(this.providerId);
        dest.writeString(this.providerCode);
        dest.writeTypedList(this.mAmounts);
        dest.writeStringList(this.arrSpin);
        dest.writeDouble(this.disCountDouble);
    }

    protected ItemCard(Parcel in) {
        this.nameHomeNetWork = in.readString();
        this.imgLogo = in.readInt();
        this.isWatch = in.readByte() != 0;
        this.disCount = in.readString();
        this.providerId = in.readInt();
        this.providerCode = in.readString();
        this.mAmounts = in.createTypedArrayList(Amount.CREATOR);
        this.arrSpin = in.createStringArrayList();
        this.disCountDouble = in.readDouble();
    }

    public static final Creator<ItemCard> CREATOR = new Creator<ItemCard>() {
        @Override
        public ItemCard createFromParcel(Parcel source) {
            return new ItemCard(source);
        }

        @Override
        public ItemCard[] newArray(int size) {
            return new ItemCard[size];
        }
    };
}
