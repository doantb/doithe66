package vn.doithe66.doithe66.model;

public class HistoryDetail {
    private String sTypeOfTransaction;
    private int Image;
    private int ColorItem;

    public HistoryDetail(String sTypeOfTransaction, int image, int colorItem) {
        this.sTypeOfTransaction = sTypeOfTransaction;
        Image = image;
        ColorItem = colorItem;
    }

    public int getColorItem() {
        return ColorItem;
    }

    public void setColorItem(int colorItem) {
        ColorItem = colorItem;
    }

    public HistoryDetail() {
    }

    public String getsTypeOfTransaction() {
        return sTypeOfTransaction;
    }

    public void setsTypeOfTransaction(String sTypeOfTransaction) {
        this.sTypeOfTransaction = sTypeOfTransaction;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}