package vn.doithe66.doithe66.model;

/**
 * Created by Windows 10 Now on 1/8/2018.
 */

public class ItemNav {
    private int icon;
    private String title;

    public ItemNav() {
    }

    public ItemNav(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
