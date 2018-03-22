package vn.doithe66.doithe66.presenter;

import vn.doithe66.doithe66.model.InfoUserEdit;

/**
 * Created by Windows 10 Now on 1/25/2018.
 */

public interface PayBankIterator {
    void getLinkPayBank(int positionFragment, String token, InfoUserEdit infoUserEdit, String url,
            OnGetUrlFinishedListener onGetUrlFinishedListener);

    void getListCard(int positionFragment, String token, InfoUserEdit infoUserEdit,
            OnGetListCardFinishedListener onGetListCardFinishedListener);
}
