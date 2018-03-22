package vn.doithe66.doithe66.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell Precision on 11/25/2017.
 */

public class DiscountNumber {
    @SerializedName("ProviderId") /*ProviderId la truong trong json tra ve*/
    private String ProviderId;

    @SerializedName("Price")
    private double Price;

    public DiscountNumber(String ProviderId, double Price) {
        this.ProviderId = ProviderId;
        this.Price = Price;
    }

    public String getProviderId() {
        return ProviderId;
    }

    public void setProviderId(String providerId) {
        this.ProviderId = providerId;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }
}
