package vn.doithe66.doithe66.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by admin on 7/13/17.
 */

public class UserInfo implements Parcelable {
    @SerializedName("UserName")
    String UserName;

    @SerializedName("Name")
    String Name;

    @SerializedName("CreateDate")
    String CreateDate;

    @SerializedName("Address")
    String Address;

    @SerializedName("Phone")
    String Phone;

    @SerializedName("BankNumber")
    String BankNumber;

    @SerializedName("BankAccount")
    String BankAccount;

    @SerializedName("BankCode")
    String BankCode;

    @SerializedName("BankName")
    String BankName;

    @SerializedName("BankBranch")
    String BankBranch;

    @SerializedName("SoDuKhaDung")
    double SoDuKhaDung;

    @SerializedName("SoDuDongBang")
    double SoDuDongBang;

    @SerializedName("ChoXuLy")
    double ChoXuLy;

    private String passWord;

    public UserInfo() {
    }

    public UserInfo(String userName, String name, String createDate, String address, String phone, String bankNumber, String bankAccount, String bankCode, String bankName, String bankBranch, double soDuKhaDung, double soDuDongBang, double choXuLy) {
        UserName = userName;
        Name = name;
        CreateDate = createDate;
        Address = address;
        Phone = phone;
        BankNumber = bankNumber;
        BankAccount = bankAccount;
        BankCode = bankCode;
        BankName = bankName;
        BankBranch = bankBranch;
        SoDuKhaDung = soDuKhaDung;
        SoDuDongBang = soDuDongBang;
        ChoXuLy = choXuLy;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public double getChoXuLy() {
        return ChoXuLy;
    }

    public void setChoXuLy(double choXuLy) {
        ChoXuLy = choXuLy;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getBankNumber() {
        return BankNumber;
    }

    public void setBankNumber(String bankNumber) {
        BankNumber = bankNumber;
    }

    public String getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(String bankAccount) {
        BankAccount = bankAccount;
    }

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String bankCode) {
        BankCode = bankCode;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBankBranch() {
        return BankBranch;
    }

    public void setBankBranch(String bankBranch) {
        BankBranch = bankBranch;
    }

    public double getSoDuKhaDung() {
        return SoDuKhaDung;
    }

    public void setSoDuKhaDung(double soDuKhaDung) {
        SoDuKhaDung = soDuKhaDung;
    }

    public double getSoDuDongBang() {
        return SoDuDongBang;
    }

    public void setSoDuDongBang(double soDuDongBang) {
        SoDuDongBang = soDuDongBang;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.UserName);
        dest.writeString(this.Name);
        dest.writeString(this.CreateDate);
        dest.writeString(this.Address);
        dest.writeString(this.Phone);
        dest.writeString(this.BankNumber);
        dest.writeString(this.BankAccount);
        dest.writeString(this.BankCode);
        dest.writeString(this.BankName);
        dest.writeString(this.BankBranch);
        dest.writeDouble(this.SoDuKhaDung);
        dest.writeDouble(this.SoDuDongBang);
        dest.writeDouble(this.ChoXuLy);
        dest.writeString(this.passWord);
    }

    protected UserInfo(Parcel in) {
        this.UserName = in.readString();
        this.Name = in.readString();
        this.CreateDate = in.readString();
        this.Address = in.readString();
        this.Phone = in.readString();
        this.BankNumber = in.readString();
        this.BankAccount = in.readString();
        this.BankCode = in.readString();
        this.BankName = in.readString();
        this.BankBranch = in.readString();
        this.SoDuKhaDung = in.readDouble();
        this.SoDuDongBang = in.readDouble();
        this.ChoXuLy = in.readDouble();
        this.passWord = in.readString();
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
