package vn.doithe66.doithe66.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by admin on 7/13/17.
 */

public class UserInfo implements Serializable {
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
    public String toString() {
        return "UserInfo{" +
                "UserName='" + UserName + '\'' +
                ", Name='" + Name + '\'' +
                ", CreateDate='" + CreateDate + '\'' +
                ", Address='" + Address + '\'' +
                ", Phone='" + Phone + '\'' +
                ", BankNumber='" + BankNumber + '\'' +
                ", BankAccount='" + BankAccount + '\'' +
                ", BankCode='" + BankCode + '\'' +
                ", BankName='" + BankName + '\'' +
                ", BankBranch='" + BankBranch + '\'' +
                ", SoDuKhaDung=" + SoDuKhaDung +
                ", SoDuDongBang=" + SoDuDongBang +
                ", ChoXuLy=" + ChoXuLy +
                '}';
    }
}
