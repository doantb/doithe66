package vn.doithe66.doithe66.model;

import java.io.Serializable;

/**
 * Created by Windows 10 Now on 11/30/2017.
 */

public class InfoUserEdit implements Serializable {
    private String typeCard;
    private int countBuy;
    private String numberPhone;
    private String typeOfPay;
    private String price;
    private String email;
    private String bankCode;
    private int providerId;
    private String providerCode;
    private String passlv2;

    private String AccountFee;

    public InfoUserEdit(int countBuy, int providerId, String price, String email) {
        this.countBuy = countBuy;
        this.providerId = providerId;
        this.price = price;
        this.email = email;
    }

    public InfoUserEdit(String typeCard, String providerCode, int countBuy, int providerId, String price,
                        String email) {
        this.typeCard = typeCard;
        this.providerCode = providerCode;
        this.countBuy = countBuy;
        this.providerId = providerId;
        this.price = price;
        this.email = email;
    }

    public InfoUserEdit(String providerCode, int countBuy, int providerId, String price, String email) {
        this.providerCode = providerCode;
        this.countBuy = countBuy;
        this.providerId = providerId;
        this.price = price;
        this.email = email;
    }


//    public InfoUserEdit(String typeCard, int countBuy, int providerId, String price, String email) {
//        this.typeCard = typeCard;
//        this.countBuy = countBuy;
//        this.price = price;
//        this.email = email;
//        this.providerId = providerId;
//    }

    public InfoUserEdit(String accountFee, String price, String email) {
        this.price = price;
        this.email = email;
        AccountFee = accountFee;
    }

    public InfoUserEdit() {
    }

    public InfoUserEdit(String sNumberFone, String sTypeLoadMoney, String sChosePrice,
                        String sEmail) {
        this.numberPhone = sNumberFone;
        this.typeOfPay = sTypeLoadMoney;
        this.price = sChosePrice;
        this.email = sEmail;
    }

    public String getPasslv2() {
        return passlv2;
    }

    public void setPasslv2(String passlv2) {
        this.passlv2 = passlv2;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getTypeOfPay() {
        return typeOfPay;
    }

    public void setTypeOfPay(String typeOfPay) {
        this.typeOfPay = typeOfPay;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountFee() {
        return AccountFee;
    }

    public void setAccountFee(String accountFee) {
        AccountFee = accountFee;
    }

    public String getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public int getCountBuy() {
        return countBuy;
    }

    public void setCountBuy(int countBuy) {
        this.countBuy = countBuy;
    }
}
