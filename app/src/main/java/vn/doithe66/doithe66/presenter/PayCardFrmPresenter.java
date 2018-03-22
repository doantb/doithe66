package vn.doithe66.doithe66.presenter;

import java.util.ArrayList;
import vn.doithe66.doithe66.model.ItemCard;

/**
 * Created by Windows 10 Now on 1/11/2018.
 */

public interface PayCardFrmPresenter {
    public void getDataPayCard(ArrayList<ItemCard> itemCards, ArrayList<String> sListTypeCard,
            ArrayList<String> sListPrice, int i);

    public void getDataPrice(ArrayList<ItemCard> itemCards, ArrayList<String> sPrice, int i);
}
