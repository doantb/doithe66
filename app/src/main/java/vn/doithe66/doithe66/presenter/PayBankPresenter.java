package vn.doithe66.doithe66.presenter;

import vn.doithe66.doithe66.model.InfoUserEdit;

/**
 * Created by Windows 10 Now on 1/25/2018.
 */

public interface PayBankPresenter {
    public void getUrlBank(int positionFragment, String token, InfoUserEdit infoUserEdit,
            String url);

    public void fromAccountMuathe(int positionFragment, String token, InfoUserEdit infoUserEdit);
}
