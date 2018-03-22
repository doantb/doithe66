package vn.doithe66.doithe66.model;

/**
 * Created by Administrator on 3/21/2018.
 */

public class ItemMenu {
    private int iconItemMenu;
    private String txtTitleItemMenu;

    public ItemMenu() {
    }

    public ItemMenu(int iconItemMenu, String txtTitleItemMenu) {
        this.iconItemMenu = iconItemMenu;
        this.txtTitleItemMenu = txtTitleItemMenu;
    }

    public int getIconItemMenu() {
        return iconItemMenu;
    }

    public void setIconItemMenu(int iconItemMenu) {
        this.iconItemMenu = iconItemMenu;
    }

    public String getTxtTitleItemMenu() {
        return txtTitleItemMenu;
    }

    public void setTxtTitleItemMenu(String txtTitleItemMenu) {
        this.txtTitleItemMenu = txtTitleItemMenu;
    }
}
