package vn.doithe66.doithe66.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by Dell Precision on 12/08/2017.
 */

public class ResultJsonApi implements Serializable {
    @SerializedName("RepCode") // gan bien nay voi truong cua json ma API tra ve:
    private int RepCode;

    //RepCode = 1 : cap nhat thong tin tai khoan ngan hang thanh cong hoac gui pass (quen pass word) vao email thanh cong.
    //RepCode = -1 : ...that bai.

    @SerializedName("Message")
    private String Message;

    @SerializedName("Data")
    private String Data;

    public ResultJsonApi(int repCode, String message, String data) {
        RepCode = repCode;
        Message = message;
        Data = data;
    }

    public int getRepCode() {
        return RepCode;
    }

    public void setRepCode(int repCode) {
        RepCode = repCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
