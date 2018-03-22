package vn.doithe66.doithe66.presenter;

import java.util.ArrayList;

import vn.doithe66.doithe66.model.ItemCard;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public interface HomeIterator {
    public void getDataFromToken(String token, ArrayList<ItemCard> itemCards,
            OnHomeGetDiscountFinishedListener listener);
}
