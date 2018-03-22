package vn.doithe66.doithe66.model;

/**
 * Created by Windows 10 Now on 11/29/2017.
 */

public class HistoryTransactionDetail {
    private String nameTran;
    private String dateTran;
    private int priceTran;

    public HistoryTransactionDetail(String nameTran, String dateTran, int priceTran) {
        this.nameTran = nameTran;
        this.dateTran = dateTran;
        this.priceTran = priceTran;
    }

    public HistoryTransactionDetail(String nameTran) {
        this.nameTran = nameTran;
    }

    public HistoryTransactionDetail() {
    }

    public String getNameTran() {
        return nameTran;
    }

    public void setNameTran(String nameTran) {
        this.nameTran = nameTran;
    }

    public String getDateTran() {
        return dateTran;
    }

    public void setDateTran(String dateTran) {
        this.dateTran = dateTran;
    }

    public int getPriceTran() {
        return priceTran;
    }

    public void setPriceTran(int priceTran) {
        this.priceTran = priceTran;
    }
}
