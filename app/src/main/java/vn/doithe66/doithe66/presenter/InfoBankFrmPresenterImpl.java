package vn.doithe66.doithe66.presenter;

public class InfoBankFrmPresenterImpl implements InfoBankFrmPresenter, OnHandlleChangeAccountBankListener {
    private InfoBankFrmIterator infoBankFrmIterator;
    private OnHandlleChangeAccountBankListener bankListener;
    private InfoBankView infoBankView;

    public InfoBankFrmPresenterImpl(InfoBankView infoBankView) {
        infoBankFrmIterator = new InfoBankFrmIteratorImpl();
        this.infoBankView = infoBankView;
    }

    @Override
    public void editInfoBank(String token, String fname, String phone, String nameBank, String numberBank, String ownBank, String branchBank) {
        infoBankFrmIterator.editAccountBank(token,fname, phone, nameBank, ownBank, branchBank,numberBank, this);
    }

    @Override
    public void onSuccessEdit(String message) {
        infoBankView.displayMessage(message);
    }

    @Override
    public void onError(String message) {
        infoBankView.displayMessage(message);
    }
}
