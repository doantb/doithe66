package vn.doithe66.doithe66.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Windows 10 Now on 1/19/2018.
 */

public class BankType implements Parcelable {
    private String bankName;
    private String bankCode;
    private int logoOfBank;
    private boolean bIsClick;
    private String urlPayBank;

    public BankType(String bankName, int logoOfBank) {
        this.bankName = bankName;
        this.logoOfBank = logoOfBank;
    }

    public BankType(String bankName, String bankCode, int logoOfBank) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.logoOfBank = logoOfBank;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getUrlPayBank() {
        return urlPayBank;
    }

    public void setUrlPayBank(String urlPayBank) {
        this.urlPayBank = urlPayBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getLogoOfBank() {
        return logoOfBank;
    }

    public void setLogoOfBank(int logoOfBank) {
        this.logoOfBank = logoOfBank;
    }

    public boolean isbIsClick() {
        return bIsClick;
    }

    public void setbIsClick(boolean bIsClick) {
        this.bIsClick = bIsClick;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bankName);
        dest.writeString(this.bankCode);
        dest.writeInt(this.logoOfBank);
        dest.writeByte(this.bIsClick ? (byte) 1 : (byte) 0);
        dest.writeString(this.urlPayBank);
    }

    protected BankType(Parcel in) {
        this.bankName = in.readString();
        this.bankCode = in.readString();
        this.logoOfBank = in.readInt();
        this.bIsClick = in.readByte() != 0;
        this.urlPayBank = in.readString();
    }

    public static final Parcelable.Creator<BankType> CREATOR = new Parcelable.Creator<BankType>() {
        @Override
        public BankType createFromParcel(Parcel source) {
            return new BankType(source);
        }

        @Override
        public BankType[] newArray(int size) {
            return new BankType[size];
        }
    };
}