package vn.doithe66.doithe66.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 Now on 11/18/2017.
 */

public class RegisterMDResult implements Parcelable {
    @SerializedName("RepCode")//RepCode là thuộc tính trong json được trả về
    public int RepCode;//RepCode này là thuộc tính của APIResult,
    //khai bao như này là để nó map RepCode của json vào RepCode của object

    @SerializedName("Message")
    public String Message;

    @SerializedName("Link")
    public String Link;

    @SerializedName("Data")
    public Object Data;

    @SerializedName("Token")
    public String Token;

    @SerializedName("CodeTransferMoney")
    public String CodeTransferMoney;

    public RegisterMDResult() {
    }

    public RegisterMDResult(int repCode, String message, Object data) {
        RepCode = repCode;
        Message = message;
        Data = data;
    }

    public String getCodeTransferMoney() {
        return CodeTransferMoney;
    }

    public void setCodeTransferMoney(String codeTransferMoney) {
        CodeTransferMoney = codeTransferMoney;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        this.Link = link;
    }

    public int getRepcode() {
        return RepCode;
    }

    public void setRepcode(int repcode) {
        RepCode = repcode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    @Override
    public String toString() {
        return "APIResult{"
                + "RepCode="
                + RepCode
                + ", Message='"
                + Message
                + '\''
                + ", Data="
                + Data
                + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.RepCode);
        dest.writeString(this.Message);
        dest.writeString(this.Link);
        dest.writeString(this.Token);
        dest.writeString(this.CodeTransferMoney);
    }

    protected RegisterMDResult(Parcel in) {
        this.RepCode = in.readInt();
        this.Message = in.readString();
        this.Link = in.readString();
        this.Data = in.readParcelable(Object.class.getClassLoader());
        this.Token = in.readString();
        this.CodeTransferMoney = in.readString();
    }

    public static final Parcelable.Creator<RegisterMDResult> CREATOR = new Parcelable.Creator<RegisterMDResult>() {
        @Override
        public RegisterMDResult createFromParcel(Parcel source) {
            return new RegisterMDResult(source);
        }

        @Override
        public RegisterMDResult[] newArray(int size) {
            return new RegisterMDResult[size];
        }
    };
}
