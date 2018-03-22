package vn.doithe66.doithe66.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell Precision on 12/20/2017.
 */

public class DataResultHistory {
    @SerializedName("Type")
    private String type;
    @SerializedName("Email")
    private String email;
    @SerializedName("StrPrice")
    private String strPrice;
    @SerializedName("StrAmount")
    private String strAmount;
    @SerializedName("CreateDate")
    private String createDate;
    @SerializedName("Status")
    private String status;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("TelcoCode")
    private String telcoCode; /*loai the khach mua*/
    @SerializedName("ChietKhau")
    private String discount;


    public DataResultHistory() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStrPrice() {
        return strPrice;
    }

    public void setStrPrice(String strPrice) {
        this.strPrice = strPrice;
    }

    public String getStrAmount() {
        return strAmount;
    }

    public void setStrAmount(String strAmount) {
        this.strAmount = strAmount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelcoCode() {
        return telcoCode;
    }

    public void setTelcoCode(String telcoCode) {
        this.telcoCode = telcoCode;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
