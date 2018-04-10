package vn.doithe66.doithe66.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.ConfigListBank;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.adapter.SpinnerChooseBankAdapter;
import vn.doithe66.doithe66.config.ConfigSpinner;
import vn.doithe66.doithe66.model.BankType;
import vn.doithe66.doithe66.model.UserInfo;
import vn.doithe66.doithe66.presenter.InfoBankFrmPresenter;
import vn.doithe66.doithe66.presenter.InfoBankFrmPresenterImpl;
import vn.doithe66.doithe66.presenter.InfoBankView;

import static vn.doithe66.doithe66.Utils.Constant.USER_INFO;

/**
 * Created by Windows 10 Now on 1/19/2018.
 */

public class InfoBankFragment extends BaseFragment implements AdapterView.OnItemSelectedListener,InfoBankView {
    @BindView(R.id.frm_bank_name)
    EditText mFrmBankName;
    @BindView(R.id.frm_bank_phone)
    EditText mFrmBankPhone;
    @BindView(R.id.frm_bank_btn_edit_info)
    Button mFrmBankBtnEditInfo;
    @BindView(R.id.frm_bank_txt_name_bank)
    TextView frmBankTxtNameBank;
    @BindView(R.id.spn_load_bank_item)
    Spinner spnLoadBankItem;
    @BindView(R.id.frm_bank_edt_number_bank)
    EditText frmBankEdtNumberBank;
    @BindView(R.id.frm_bank_own_account)
    EditText frmBankOwnAccount;
    @BindView(R.id.frm_bank_edt_name_branch)
    EditText frmBankEdtNameBranch;


    private UserInfo mUserInfo;
    private SpinnerChooseBankAdapter spinnerChooseBankAdapter;
    private ArrayList<BankType> bankTypes;
    private InfoBankFrmPresenter infoBankFrmPresenter;
    private String token;

    public static InfoBankFragment newInstance(UserInfo userInfo) {
        InfoBankFragment infoBankFragment = new InfoBankFragment();
        Bundle args = new Bundle();
        args.putParcelable(USER_INFO, userInfo);
        infoBankFragment.setArguments(args);
        return infoBankFragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_info_bank;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        infoBankFrmPresenter = new InfoBankFrmPresenterImpl(this);
        token = SharedPrefs.getInstance().get(Constant.ACCESS_TOKEN, String.class);
        if (!ConfigListBank.isInit) {
            ConfigListBank.initDataBank(bankTypes);
        }
        bankTypes = ConfigListBank.getListBank();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUserInfo = (UserInfo) bundle.getParcelable(USER_INFO);
        }
        mFrmBankName.setText(mUserInfo.getName());
        mFrmBankPhone.setText(mUserInfo.getPhone());
        frmBankEdtNameBranch.setText(mUserInfo.getBankBranch());
        frmBankEdtNumberBank.setText(mUserInfo.getBankNumber());
        frmBankOwnAccount.setText(mUserInfo.getBankAccount());
        frmBankTxtNameBank.setText(mUserInfo.getBankName());
        ConfigSpinner.setupSpinnnerBank(getContext(), spnLoadBankItem, spinnerChooseBankAdapter, bankTypes);
        spnLoadBankItem.setOnItemSelectedListener(this);
        setSelectionItemSpinner();
    }

    private void setSelectionItemSpinner() {
        if (mUserInfo.getBankCode() != null && mUserInfo.getBankCode() != "") {
            for (int i = 0; i < bankTypes.size(); i++) {
                if (bankTypes.get(i).getBankCode().equalsIgnoreCase(mUserInfo.getBankCode())) {
                    spnLoadBankItem.setSelection(i);
                }
            }
        }
    }

    @OnClick(R.id.frm_bank_btn_edit_info)
    public void onViewClicked() {
        String fname = mFrmBankName.getText().toString();
        String phone = mFrmBankPhone.getText().toString();
        String nameBank = frmBankTxtNameBank.getText().toString();
        String numberBank = frmBankEdtNumberBank.getText().toString();
        String ownBank = frmBankOwnAccount.getText().toString();
        String branchBank = frmBankEdtNameBranch.getText().toString();
        if (fname.equals("") || fname == null) {
            mFrmBankName.setError("Vui lòng nhập họ tên");
        } else if (phone.equals("") || phone == null) {
            mFrmBankPhone.setError("Vui lòng nhập số điện thoại");
        } else if (nameBank.equals("") || nameBank == null) {
            frmBankTxtNameBank.setError("Vui lòng nhập tên ngân hàng");
        } else if (numberBank.equals("") || numberBank == null) {
            frmBankEdtNumberBank.setError("Vui lòng nhập số tài khoản");
        } else if (ownBank.equals("") || ownBank == null) {
            frmBankOwnAccount.setError("Vui lòng nhập tên chủ tài khoản");
        } else if (branchBank.equals("") || branchBank == null) {
            frmBankEdtNameBranch.setError("Vui lòng nhập tên chi nhánh ngân hàng");
        } else {
            infoBankFrmPresenter.editInfoBank(token, fname, phone, bankTypes.get(spnLoadBankItem.getSelectedItemPosition()).getBankCode(), numberBank, ownBank, branchBank);
        }
        return;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        frmBankTxtNameBank.setText(bankTypes.get(i).getBankName());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(getActivity(), message+" ,Bạn cần vào email để xác nhận cập nhật", Toast.LENGTH_SHORT).show();
    }
}
