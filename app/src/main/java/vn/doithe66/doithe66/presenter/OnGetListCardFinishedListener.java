package vn.doithe66.doithe66.presenter;

import vn.doithe66.doithe66.model.ResultCardDoithe;

/**
 * Created by Windows 10 Now on 1/26/2018.
 */

public interface OnGetListCardFinishedListener {
    void onGetListCardSuccess(ResultCardDoithe resultCardDoithe, String sMessage);

    void onPayMoneyForMobile(String message);

    void onErrorGetListCard();
}
