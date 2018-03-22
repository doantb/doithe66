package vn.doithe66.doithe66.model;

/**
 * Created by Windows 10 Now on 1/19/2018.
 */

public class BankType {
    private String bankName;
    private int logoOfBank;
    private boolean bIsClick;
    private String urlPayBank;

    public BankType(String bankName, int logoOfBank) {
        this.bankName = bankName;
        this.logoOfBank = logoOfBank;
    }

    public String getUrlPayBank() {
        return urlPayBank;
    }

    public void setUrlPayBank(String urlPayBank) {
        this.urlPayBank = urlPayBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getLogoOfBank() {
        return logoOfBank;
    }

    public void setLogoOfBank(int logoOfBank) {
        this.logoOfBank = logoOfBank;
    }

    public boolean isbIsClick() {
        return bIsClick;
    }

    public void setbIsClick(boolean bIsClick) {
        this.bIsClick = bIsClick;
    }
}