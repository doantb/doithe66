package vn.doithe66.doithe66.model;

import com.google.gson.annotations.SerializedName;
import java.text.DecimalFormat;

/**
 * Created by Windows 10 Now on 11/25/2017.
 */

public class GetDiscountCard {
    @SerializedName("ProviderId")//RepCode là thuộc tính trong json được trả về
    public int ProviderId;//RepCode này là thuộc tính của APIResult,
    //khai bao như này là để nó map RepCode của json vào RepCode của object

    @SerializedName("Price")
    public double Price;
    @SerializedName("ProviderCode")
    public String ProviderCode;

    @SerializedName("data")
    public String Data;

    @SerializedName("Description")
    private String Description;

    public int getProviderId() {
        return ProviderId;
    }

    public void setProviderId(int providerId) {
        ProviderId = providerId;
    }

    public String getPrice() {
        return makeData();
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getProviderCode() {
        return ProviderCode;
    }

    public void setProviderCode(String providerCode) {
        ProviderCode = providerCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "APIResult{"
                + "ProviderId="
                + ProviderId
                + ", Message='"
                + Price
                + '\''
                + ", Data="
                + Data
                + '}';
    }

    public String makeData() {
        return new DecimalFormat("##.##").format(100 - Price) + "%";
    }
}
