package vn.doithe66.doithe66.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/**
 * Created by Windows 10 Now on 1/10/2018.
 */

public class ItemCard implements Parcelable{
    private String nameHomeNetWork;
    private int imgLogo;
    private boolean isWatch = false;
    private String disCount;
    private int providerId;
    private String providerCode;
    private ArrayList<Amount> mAmounts;
    private ArrayList<String> arrSpin;

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

    protected ItemCard(Parcel in) {
        nameHomeNetWork = in.readString();
        imgLogo = in.readInt();
        isWatch = in.readByte() != 0;
        disCount = in.readString();
        providerId = in.readInt();
        providerCode = in.readString();
        arrSpin = in.createStringArrayList();
    }

    public static final Creator<ItemCard> CREATOR = new Creator<ItemCard>() {
        @Override
        public ItemCard createFromParcel(Parcel in) {
            return new ItemCard(in);
        }

        @Override
        public ItemCard[] newArray(int size) {
            return new ItemCard[size];
        }
    };

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public ArrayList<String> getArrSpin() {
        return arrSpin;
    }

    public void setArrSpin(ArrayList<String> arrSpin) {
        this.arrSpin = arrSpin;
    }

    public ArrayList<Amount> getAmounts() {
        return mAmounts;
    }

    public void setAmounts(ArrayList<Amount> amounts) {
        mAmounts = amounts;
    }

    public String getDisCount() {
        return disCount;
    }

    public void setDisCount(String disCount) {
        this.disCount = disCount;
    }

    public boolean isWatch() {
        return isWatch;
    }

    public void setWatch(boolean watch) {
        isWatch = watch;
    }

    public String getNameHomeNetWork() {
        return nameHomeNetWork;
    }

    public void setNameHomeNetWork(String nameHomeNetWork) {
        this.nameHomeNetWork = nameHomeNetWork;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(int imgLogo) {
        this.imgLogo = imgLogo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        ItemCard itemCompare = (ItemCard) obj;
        if (itemCompare.getNameHomeNetWork().equals(this.getNameHomeNetWork())) return true;

        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameHomeNetWork);
        parcel.writeInt(imgLogo);
        parcel.writeByte((byte) (isWatch ? 1 : 0));
        parcel.writeString(disCount);
        parcel.writeInt(providerId);
        parcel.writeString(providerCode);
        parcel.writeStringList(arrSpin);
    }
}
