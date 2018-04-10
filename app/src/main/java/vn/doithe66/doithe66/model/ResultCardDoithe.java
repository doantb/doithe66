package vn.doithe66.doithe66.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultCardDoithe implements Parcelable {

    /**
     * RepCode : 1
     * Message : Mua mã thẻ thành công.
     * Data : [{"ProviderCode":"VTT","Serial":"10000478516132","PinCode":"713187805185389\u0001","Amount":10000}]
     */
    @SerializedName("RepCode")
    private int RepCode;

    @SerializedName("Message")
    private String Message;

    @SerializedName("Data")
    private List<DataBean> Data;

    public int getRepCode() {
        return RepCode;
    }

    public void setRepCode(int RepCode) {
        this.RepCode = RepCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean implements Parcelable {
        /**
         * ProviderCode : VTT
         * Serial : 10000478516132
         * PinCode : 713187805185389
         * Amount : 10000
         */
        @SerializedName("ProviderCode")
        private String ProviderCode;

        @SerializedName("Serial")
        private String Serial;

        @SerializedName("PinCode")
        private String PinCode;

        @SerializedName("Amount")
        private int Amount;

        public String getProviderCode() {
            return ProviderCode;
        }

        public void setProviderCode(String ProviderCode) {
            this.ProviderCode = ProviderCode;
        }

        public String getSerial() {
            return Serial;
        }

        public void setSerial(String Serial) {
            this.Serial = Serial;
        }

        public String getPinCode() {
            return PinCode;
        }

        public void setPinCode(String PinCode) {
            this.PinCode = PinCode;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
            this.Amount = Amount;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ProviderCode);
            dest.writeString(this.Serial);
            dest.writeString(this.PinCode);
            dest.writeInt(this.Amount);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.ProviderCode = in.readString();
            this.Serial = in.readString();
            this.PinCode = in.readString();
            this.Amount = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.RepCode);
        dest.writeString(this.Message);
        dest.writeList(this.Data);
    }

    public ResultCardDoithe() {
    }

    protected ResultCardDoithe(Parcel in) {
        this.RepCode = in.readInt();
        this.Message = in.readString();
        this.Data = new ArrayList<DataBean>();
        in.readList(this.Data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ResultCardDoithe> CREATOR = new Parcelable.Creator<ResultCardDoithe>() {
        @Override
        public ResultCardDoithe createFromParcel(Parcel source) {
            return new ResultCardDoithe(source);
        }

        @Override
        public ResultCardDoithe[] newArray(int size) {
            return new ResultCardDoithe[size];
        }
    };
}
