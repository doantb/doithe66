package vn.doithe66.doithe66.presenter;

import java.util.ArrayList;
import vn.doithe66.doithe66.model.DataResultHistory;
import vn.doithe66.doithe66.model.HistoryDetail;
import vn.doithe66.doithe66.view.HistoryFrmView;

/**
 * Created by Windows 10 Now on 1/20/2018.
 */

public class HistoryFrmPresenterImpl implements HistoryFrmPresenter, OnSeeHistoryFinishListener {
    private HistoryFrmView mHistoryFrmView;
    private HistoryIterator mHistoryIterator;
//    private ProgessView mProgessView;

    public HistoryFrmPresenterImpl(HistoryFrmView historyFrmView) {
        mHistoryFrmView = historyFrmView;
//        mProgessView = progessView;
        mHistoryIterator = new HistoryInteratorImpl();
    }

    @Override
    public void checkToken(String token) {

    }

    @Override
    public void initDataHistory(ArrayList<HistoryDetail> historyDetails) {
        mHistoryIterator.initDataForListHistory(historyDetails);
    }

    @Override
    public void getDataForHistoryDetail(ArrayList<DataResultHistory> dataResultHistory,
            int position, String frDate, String toDate, String sFoneItemHistory, String sPrice,
            String token) {
//        mProgessView.showProgess();
        mHistoryIterator.getDataForHistoryDetail(dataResultHistory, position, frDate, toDate,
                sFoneItemHistory, sPrice, token, this);
    }

    @Override
    public void onSuccess() {
//        mProgessView.hideProgess();
        mHistoryFrmView.toFrmHistoryDetail();
    }

    @Override
    public void onErrorNumberPhone() {
        mHistoryFrmView.onErrorPickPhone();
    }

    @Override
    public void onErrorDate() {
//        mProgessView.hideProgess();
        mHistoryFrmView.onErorPickDate();
    }
}
