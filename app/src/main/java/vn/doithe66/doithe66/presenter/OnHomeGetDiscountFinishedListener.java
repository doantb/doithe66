package vn.doithe66.doithe66.presenter;

import java.util.ArrayList;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.model.UserInfo;

/**
 * Created by Windows 10 Now on 1/23/2018.
 */

public interface OnHomeGetDiscountFinishedListener {
    public void onErrorToken();

    public void onSuccess(ArrayList<ItemCard> itemCards);

    public void onSuccessUserInfo(UserInfo userInfo);
}
