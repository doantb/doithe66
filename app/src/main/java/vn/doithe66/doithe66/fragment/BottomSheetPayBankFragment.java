package vn.doithe66.doithe66.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import java.util.ArrayList;
import vn.doithe66.doithe66.NotificationActivity;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.activity.ResultDetailNoBankActivity;
import vn.doithe66.doithe66.adapter.PayBankAdapter;
import vn.doithe66.doithe66.model.BankType;
import vn.doithe66.doithe66.model.InfoUserEdit;
import vn.doithe66.doithe66.presenter.PayBankGetDataImplIterator;
import vn.doithe66.doithe66.presenter.PayBankPresenter;
import vn.doithe66.doithe66.presenter.PayBankPresenterImpl;
import vn.doithe66.doithe66.view.PayBankGetDataForListIterator;
import vn.doithe66.doithe66.view.PayBankView;
import vn.doithe66.doithe66.view.ProgessView;

import static vn.doithe66.doithe66.Utils.Constant.ACCESS_TOKEN;
import static vn.doithe66.doithe66.Utils.Constant.FROM_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.FROM_PAY_BANK;
import static vn.doithe66.doithe66.Utils.Constant.LIST_CARD;
import static vn.doithe66.doithe66.Utils.Constant.URL_PAY_BANK;

/**
 * Created by Windows 10 Now on 1/19/2018.
 */

public class BottomSheetPayBankFragment extends BottomSheetDialogFragment
        implements PayBankAdapter.OnClickItemBank, ProgessView, PayBankView {
    @BindView(R.id.rb_item_banthe)
    RadioButton mRbItemBanthe;
    @BindView(R.id.img_item_recycle_pay_bank)
    ImageView mImgItemRecyclePayBank;
    @BindView(R.id.rl_ban_the)
    RelativeLayout mRlBanThe;
    @BindView(R.id.txt_pay_with)
    TextView mTxtPayWith;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.recycle_pay_bank)
    RecyclerView mRecyclePayBank;
    @BindView(R.id.btn_click_choose_bank)
    Button mBtnClickChooseBank;
    @BindView(R.id.my_progess_bar)
    RelativeLayout mMyProgessBar;
    private Context mContext;
    private ArrayList<BankType> bankTypes;
    private PayBankAdapter mPayBankAdapter;
    private PayBankGetDataForListIterator mIterator;
    private int fromFragment;
    private RecyclerView.LayoutManager mLayoutManager;
    private InfoUserEdit mUserEdit;
    private String token;
    private String url;
    private Unbinder mUnbinder;

    public BottomSheetPayBankFragment() {
    }

    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            fromFragment = bundle.getInt(FROM_FRAGMENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_pay_banl, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        token = SharedPrefs.getInstance().get(ACCESS_TOKEN, String.class);
        Bundle bundle = getArguments();
        if (bundle != null) {
            fromFragment = bundle.getInt(Constant.FROM_FRAGMENT, 0);
            mUserEdit = (InfoUserEdit) bundle.getSerializable(Constant.INFO_USER_EDIT);
        } else {
            mUserEdit = new InfoUserEdit();
        }
        bankTypes = new ArrayList<>();
        mIterator = new PayBankGetDataImplIterator();
        mIterator.initDataForPayBank(fromFragment, bankTypes);
        mPayBankAdapter = new PayBankAdapter(mContext, bankTypes, this);
        mLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclePayBank.setHasFixedSize(true);
        mRecyclePayBank.setLayoutManager(mLayoutManager);
        mRecyclePayBank.setAdapter(mPayBankAdapter);
        return view;
    }

    @Override
    public void onClickBank(int position) {
        mUserEdit.setBankCode(bankTypes.get(position).getBankName());
        mPayBankAdapter.updateAll();
        bankTypes.get(position).setbIsClick(true);
        mRbItemBanthe.setChecked(false);
        mPayBankAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.rl_ban_the)
    public void onViewClickedMuathe() {
        mPayBankAdapter.updateAll();
        mPayBankAdapter.notifyDataSetChanged();
        mRbItemBanthe.setChecked(true);
    }

    @Override
    public void showProgess() {
        mMyProgessBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgess() {
        mMyProgessBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoadUrlSuccess(String url) {
        Intent intent = new Intent(getActivity(), NotificationActivity.class);
        intent.putExtra(FROM_PAY_BANK, 1);
        intent.putExtra(URL_PAY_BANK, url);
        startActivity(intent);
    }

    @Override
    public void onGetListCardSuccess(String sListCard, String message) {
        if (sListCard != null && !sListCard.isEmpty()) {
            Toast.makeText(getActivity(), message + "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), ResultDetailNoBankActivity.class);
            intent.putExtra(LIST_CARD, sListCard);
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), message + "", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_click_choose_bank)
    public void onViewClicked() {
        PayBankPresenter payBankPresenter = new PayBankPresenterImpl(this, this);
        if (mRbItemBanthe.isChecked()) {
            payBankPresenter.fromAccountMuathe(fromFragment, token, mUserEdit);
        } else {
            payBankPresenter.getUrlBank(fromFragment, token, mUserEdit, url);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        mUnbinder.unbind();
    }
}
