package vn.doithe66.doithe66.presenter;

import java.util.ArrayList;
import vn.doithe66.doithe66.model.DataResultHistory;
import vn.doithe66.doithe66.model.HistoryDetail;

/**
 * Created by Windows 10 Now on 1/20/2018.
 */

public interface HistoryIterator {
    public void initDataForListHistory(ArrayList<HistoryDetail> historyDetails);

    public void getDataForHistoryDetail(ArrayList<DataResultHistory> dataResultHistory,
            int position, String frDate, String toDate, String sFoneItemHistory, String sPrice,
            String token,OnSeeHistoryFinishListener onSeeHistoryFinishListener);
}
