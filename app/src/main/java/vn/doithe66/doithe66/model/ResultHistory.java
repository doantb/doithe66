package vn.doithe66.doithe66.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Windows 10 Now on 12/7/2017.
 */

public class ResultHistory {
    @SerializedName("RepCode")//RepCode là thuộc tính trong json được trả về
    public int RepCode;//RepCode này là thuộc tính của APIResult,
    //khai bao như này là để nó map RepCode của json vào RepCode của object

    @SerializedName("Message")
    public String Message;

    @SerializedName("TotalRecord")
    public int TotalRecord;

    @SerializedName("Data")
    public Object Data;

    public ResultHistory() {
    }

    public ResultHistory(int repCode, String message, int totalRecord, Object data) {
        RepCode = repCode;
        Message = message;
        TotalRecord = totalRecord;
        Data = data;
    }

    public int getTotalRecord() {
        return TotalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        TotalRecord = totalRecord;
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
}
