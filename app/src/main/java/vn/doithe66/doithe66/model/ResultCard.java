package vn.doithe66.doithe66.model;

/**
 * Created by Windows 10 Now on 12/25/2017.
 */

public class ResultCard {
    private String serial;
    private String providerCode;

    public ResultCard() {
    }

    public ResultCard(String serial, String providerCode) {
        this.serial = serial;
        this.providerCode = providerCode;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }
}
