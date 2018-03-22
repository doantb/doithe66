package vn.doithe66.doithe66.presenter;

import java.util.ArrayList;

import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.view.PayCardView;
import vn.doithe66.doithe66.view.ProgessView;

/**
 * Created by Windows 10 Now on 1/11/2018.
 */

public class PayCardFrmPresenterImpl
        implements OnGetDataPayCardFinishListener, PayCardFrmPresenter {
    private ProgessView mProgessView;
    private PayCardView mPayCardView;
    private PayCardGetDataInterator mDataInterator;

    public PayCardFrmPresenterImpl(ProgessView progessView,PayCardView payCardView) {
        mProgessView = progessView;
        mPayCardView = payCardView;
        mDataInterator = new PayCardGetDataInteratorImpl();
    }

    @Override
    public void onGetDataError() {
        mProgessView.hideProgess();
    }

    @Override
    public void onSuccess() {
        mProgessView.hideProgess();
        mPayCardView.onLoadDataSuccess();
    }

    @Override
    public void getDataPayCard(ArrayList<ItemCard> itemCards, ArrayList<String> sListTypeCard,
            ArrayList<String> sListPrice, int i) {
        mProgessView.showProgess();
        mDataInterator.getData(itemCards, sListTypeCard, sListPrice, i, this);
    }

    @Override
    public void getDataPrice(ArrayList<ItemCard> itemCards, ArrayList<String> sPrice, int i) {
        mDataInterator.getDataPrice(itemCards, sPrice, i);
    }
}
