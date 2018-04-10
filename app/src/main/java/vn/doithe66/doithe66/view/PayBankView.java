package vn.doithe66.doithe66.view;

import vn.doithe66.doithe66.model.ResultCardDoithe;

/**
 * Created by Windows 10 Now on 1/25/2018.
 */

public interface PayBankView {
    void onLoadUrlSuccess(String url);

    void onGetListCardSuccess(ResultCardDoithe sListCard, String message);

    void onGetMessagePayForMobile(String message);
}
