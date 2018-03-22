package vn.doithe66.doithe66.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;

import java.text.ParseException;
import java.util.ArrayList;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.adapter.SpinnerAdapter;
import vn.doithe66.doithe66.config.ConfigSpinner;
import vn.doithe66.doithe66.model.InfoUserEdit;

import static vn.doithe66.doithe66.Utils.Constant.EMAIL;
import static vn.doithe66.doithe66.Utils.Constant.FROM_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.INFO_USER_EDIT;
import static vn.doithe66.doithe66.Utils.Constant.KEY_TOKEN;
import static vn.doithe66.doithe66.Utils.Constant.PAY_FAST_FRAGMENT;

/**
 * Created by Windows 10 Now on 1/16/2018.
 */

public class PayFastFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.ll_content_fast)
    LinearLayout mLlContentFast;
    @BindView(R.id.crv_content_fast)
    CardView mCrvContentFast;
    @BindView(R.id.crv_top)
    CardView mCrvTop;
    @BindView(R.id.frm_pay_fast_btn_buy)
    Button mFrmPayFastBtnBuy;
    @BindView(R.id.spn_load_type_of_pay)
    Spinner mSpnLoadTypeOfPay;
    @BindView(R.id.frm_pay_fast_txt_choose_price)
    TextView tvChosePrice;
    @BindView(R.id.spn_loadmoney_item)
    Spinner mSpnLoadmoneyItem;
    @BindView(R.id.frm_pay_fast_txt_choose_type_pay)
    TextView mFrmPayFastTxtChooseTypePay;
    @BindView(R.id.frm_pay_fast_edt_phone)
    EditText mFrmPayFastEdtPhone;
    @BindView(R.id.frm_pay_fast_edt_email)
    EditText mFrmPayFastEdtEmail;
    private ArrayList<String> lisChosePriceLoadMoneyFast = new ArrayList<>();
    private ArrayList<String> lisTypeLoadmoney = new ArrayList<>();
    private SpinnerAdapter spinnerAdapterChosePrice;
    private ArrayAdapter<String> arrayAdapterTypeLoadMoney;

    @Override
    protected int layoutId() {
        return R.layout.fragment_pay_fast;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        ConfigSpinner.setupSpinnner(lisChosePriceLoadMoneyFast, spinnerAdapterChosePrice,
                mSpnLoadmoneyItem, getActivity(), "");
        mFrmPayFastEdtEmail.setText(SharedPrefs.getInstance().get(EMAIL, String.class));
        mSpnLoadmoneyItem.setOnItemSelectedListener(this);
        mSpnLoadTypeOfPay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        mFrmPayFastTxtChooseTypePay.setText(lisTypeLoadmoney.get(i));
                        break;
                    case 1:
                        mFrmPayFastTxtChooseTypePay.setText(lisTypeLoadmoney.get(i));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ConfigSpinner.setupSpinnner(lisTypeLoadmoney, spinnerAdapterChosePrice, mSpnLoadTypeOfPay,
                getActivity(), Constant.TYPE_SPINNER);
        mFrmPayFastBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewClicked();
            }
        });
    }

    public static PayFastFragment newInstance(String token) {
        PayFastFragment payFastFragment = new PayFastFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TOKEN, token);
        payFastFragment.setArguments(args);
        return payFastFragment;
    }

    public void onViewClicked() {
        String sEdtPhone = mFrmPayFastEdtPhone.getText().toString().trim();
        String sTypeLoadMoney = "";
        // neu chon vi tri thu 0, hoac vi tri thu 1 thi khoi tao lai sChosePrice
        if (mSpnLoadTypeOfPay.getSelectedItemPosition() == 0) {
            mFrmPayFastTxtChooseTypePay.setText(lisTypeLoadmoney.get(0));
            sTypeLoadMoney = "TT";
        } else if (mSpnLoadTypeOfPay.getSelectedItemPosition() == 1) {
            mFrmPayFastTxtChooseTypePay.setText(lisTypeLoadmoney.get(1));
            sTypeLoadMoney = "TS";
        }
        String sChosePrice = (String) mSpnLoadmoneyItem.getSelectedItem();
        String sEmail = mFrmPayFastEdtEmail.getText().toString().trim();

        if (sEdtPhone.isEmpty()) {
            mFrmPayFastEdtPhone.setError("Vui lòng điền số điện thoại !");
        } else if (sTypeLoadMoney.isEmpty() || sChosePrice.isEmpty()) {
            Toast.makeText(getActivity(), "Vui lòng chọn đầy đủ thông tin !", Toast.LENGTH_SHORT)
                    .show();
        } else {
            BottomSheetPayBankFragment payBankFragment = new BottomSheetPayBankFragment();
            InfoUserEdit infoUserEdit = new InfoUserEdit();
            infoUserEdit.setPrice(sChosePrice);
            infoUserEdit.setEmail(sEmail);
            infoUserEdit.setTypeOfPay(sTypeLoadMoney);
            infoUserEdit.setNumberPhone(sEdtPhone);
            infoUserEdit.setEmail(SharedPrefs.getInstance().get(EMAIL, String.class));
            Bundle bundle = new Bundle();
            bundle.putInt(FROM_FRAGMENT, PAY_FAST_FRAGMENT);
            bundle.putSerializable(INFO_USER_EDIT, infoUserEdit);
            payBankFragment.setArguments(bundle);
            payBankFragment.setContext(getActivity());
            payBankFragment.show(getActivity().getSupportFragmentManager(),
                    payBankFragment.getTag());
        }
    }

    //    @OnClick({
    //            R.id.spn_load_type_of_pay, R.id.frm_pay_fast_txt_choose_price,
    //            R.id.frm_pay_fast_txt_choose_type_pay
    //    })
    //    public void onViewClicked(View view) {
    //        switch (view.getId()) {
    //            case R.id.spn_load_type_of_pay:
    //                break;
    //            case R.id.frm_pay_fast_txt_choose_price:
    //                break;
    //            case R.id.frm_pay_fast_txt_choose_type_pay:
    //                break;
    //        }
    //    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                tvChosePrice.setText(R.string.muoinghin);
                break;
            case 1:
                tvChosePrice.setText(R.string.haimuoi);
                break;
            case 2:
                tvChosePrice.setText(R.string.bamuoi);
                break;
            case 3:
                tvChosePrice.setText(R.string.nammuoi);
                break;
            case 4:
                tvChosePrice.setText(R.string.mottram);
                break;
            case 5:
                tvChosePrice.setText(R.string.haitram);
                break;
            case 6:
                tvChosePrice.setText(R.string.batram);
                break;
            case 7:
                tvChosePrice.setText(R.string.namtram);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
