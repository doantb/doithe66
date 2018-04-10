package vn.doithe66.doithe66.presenter;

import vn.doithe66.doithe66.model.InfoUserEdit;
import vn.doithe66.doithe66.model.ResultCardDoithe;
import vn.doithe66.doithe66.view.PayBankView;
import vn.doithe66.doithe66.view.ProgessView;

/**
 * Created by Windows 10 Now on 1/25/2018.
 */

public class PayBankPresenterImpl
        implements PayBankPresenter, OnGetUrlFinishedListener, OnGetListCardFinishedListener {
    private PayBankIterator mPayBankIterator;
    private ProgessView mProgessView;
    private PayBankView mPayBankView;

    public PayBankPresenterImpl(ProgessView progessView, PayBankView payBankView) {
        mProgessView = progessView;
        mPayBankView = payBankView;
        mPayBankIterator = new PayBankIteratorImpl();
    }

    @Override
    public void getUrlBank(int positionFragment, String token, InfoUserEdit infoUserEdit,
            String url) {
        mProgessView.showProgess();
        mPayBankIterator.getLinkPayBank(positionFragment, token, infoUserEdit, url, this);
    }

    @Override
    public void onSuccess(String url) {
        mProgessView.hideProgess();
        mPayBankView.onLoadUrlSuccess(url);
    }

    @Override
    public void onError() {

    }

    @Override
    public void fromAccountDoithe(int positionFragment, String token, InfoUserEdit infoUserEdit) {
        mProgessView.showProgess();
        mPayBankIterator.getListCard(positionFragment, token, infoUserEdit, this);
    }

    @Override
    public void onGetListCardSuccess(ResultCardDoithe resultCardDoithe, String sMessage) {
        mProgessView.hideProgess();
        mPayBankView.onGetListCardSuccess(resultCardDoithe, sMessage);
    }

    @Override
    public void onPayMoneyForMobile(String message) {
        mProgessView.hideProgess();
        mPayBankView.onGetMessagePayForMobile(message);
    }

    @Override
    public void onErrorGetListCard() {
        mProgessView.hideProgess();
    }
}
