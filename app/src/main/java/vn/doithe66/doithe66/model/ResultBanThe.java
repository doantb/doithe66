package vn.doithe66.doithe66.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 Now on 12/15/2017.
 */

public class ResultBanThe {
    @SerializedName("errorCode")//RepCode là thuộc tính trong json được trả về
    public int errorCode;//RepCode này là thuộc tính của APIResult,
    //khai bao như này là để nó map RepCode của json vào RepCode của object

    @SerializedName("message")
    public String message;

    @SerializedName("listCards")
    public String LinlistCardsk;

    @SerializedName("transaction")
    public String transaction;

    public ResultBanThe() {
    }

    public ResultBanThe(int errorCode, String message, String linlistCardsk, String transaction) {
        this.errorCode = errorCode;
        this.message = message;
        LinlistCardsk = linlistCardsk;
        this.transaction = transaction;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLinlistCardsk() {
        return LinlistCardsk;
    }

    public void setLinlistCardsk(String linlistCardsk) {
        LinlistCardsk = linlistCardsk;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
}
