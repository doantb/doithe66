package vn.doithe66.doithe66.presenter;

/**
 * Created by Windows 10 Now on 1/26/2018.
 */

public interface OnGetListCardFinishedListener {
    void onGetListCardSuccess(String sListCard, String sMessage);

    void onErrorGetListCard();
}
