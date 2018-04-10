package vn.doithe66.doithe66.presenter;

import java.util.ArrayList;

import vn.doithe66.doithe66.model.Amount;
import vn.doithe66.doithe66.model.ItemCard;

/**
 * Created by Windows 10 Now on 1/11/2018.
 */

public class PayCardGetDataInteratorImpl implements PayCardGetDataInterator {
    @Override
    public void getData(ArrayList<ItemCard> itemCards, ArrayList<String> sListTypeCard,
            ArrayList<String> sListPrice, int i,
            OnGetDataPayCardFinishListener onGetDataPayCardFinishListener) {
        sListTypeCard.addAll(initDataTypeCard(itemCards));
        sListPrice.addAll(initDataPrice(itemCards, i));
        onGetDataPayCardFinishListener.onSuccess();
        //        if (itemCards != null) {
        //            onGetDataPayCardFinishListener.onSuccess();
        //        } else {
        //            onGetDataPayCardFinishListener.onGetDataError();
        //        }
    }

    @Override
    public void getDataPrice(ArrayList<ItemCard> itemCards, ArrayList<String> sPrice,
            int position) {
        sPrice.addAll(initDataPrice(itemCards, position));
    }

    private ArrayList<String> initDataPrice(ArrayList<ItemCard> mItemCards, int j) {
        ArrayList<String> sListPrice = new ArrayList<>();
        if (j == -1) {
            sListPrice.add("10.000đ");
            sListPrice.add("20.000đ");
            sListPrice.add("30.000đ");
            sListPrice.add("50.000đ");
            sListPrice.add("100.000đ");
            sListPrice.add("200.000đ");
            sListPrice.add("300.000đ");
            sListPrice.add("500.000đ");
        } else {
            ArrayList<Amount> amounts = new ArrayList<>();
            amounts.addAll(mItemCards.get(j).getmAmounts());
            for (int i = 0; i < amounts.size(); i++) {
                sListPrice.add(amounts.get(i).getCardName());
            }
        }
        return sListPrice;
    }

    private ArrayList<String> initDataTypeCard(ArrayList<ItemCard> itemCards) {
        ArrayList<String> sListTypeCard = new ArrayList<>();
        for (int i = 0; i < itemCards.size(); i++) {
            sListTypeCard.add(itemCards.get(i).getNameHomeNetWork());
        }
        return sListTypeCard;
    }
}
