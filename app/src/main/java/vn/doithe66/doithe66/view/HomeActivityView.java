package vn.doithe66.doithe66.view;

import java.util.ArrayList;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.model.UserInfo;

/**
 * Created by Windows 10 Now on 1/9/2018.
 */

public interface HomeActivityView {
    void openNav();

    void closeNav();

    void showNavFragment();

    void disPlayListCard();

    void showErrorToken();

    void showNodata();

    void reload();

    void setTittleActionBar(String title);

    void onSuccessGetData(ArrayList<ItemCard> itemCards);

    void onSuccessGetUserInfo(UserInfo userInfo);
}
