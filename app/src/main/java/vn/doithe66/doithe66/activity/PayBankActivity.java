package vn.doithe66.doithe66.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.doithe66.doithe66.NotificationActivity;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.AutoLogin;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.adapter.PayBankAdapter;
import vn.doithe66.doithe66.model.BankType;
import vn.doithe66.doithe66.model.InfoUserEdit;
import vn.doithe66.doithe66.model.ResultCardDoithe;
import vn.doithe66.doithe66.model.UserInfo;
import vn.doithe66.doithe66.presenter.PayBankGetDataImplIterator;
import vn.doithe66.doithe66.presenter.PayBankPresenter;
import vn.doithe66.doithe66.presenter.PayBankPresenterImpl;
import vn.doithe66.doithe66.view.PayBankGetDataForListIterator;
import vn.doithe66.doithe66.view.PayBankView;
import vn.doithe66.doithe66.view.ProgessView;

import static vn.doithe66.doithe66.Utils.Constant.ACCESS_TOKEN;
import static vn.doithe66.doithe66.Utils.Constant.FROM_PAY_BANK;
import static vn.doithe66.doithe66.Utils.Constant.LIST_CARD;
import static vn.doithe66.doithe66.Utils.Constant.PAY_CARD_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.URL_PAY_BANK;
import static vn.doithe66.doithe66.Utils.Constant.USER_INFO;

/**
 * Created by Windows 10 Now on 1/20/2018.
 */

public class PayBankActivity extends BaseActivity
        implements PayBankAdapter.OnClickItemBank, ProgessView, PayBankView {
    @BindView(R.id.rb_item_banthe)
    RadioButton mRbItemBanthe;
    @BindView(R.id.img_item_recycle_pay_bank)
    ImageView mImgItemRecyclePayBank;
    @BindView(R.id.rl_ban_the)
    RelativeLayout mRlBanThe;
    //    @BindView(R.id.txt_pay_with)
//    TextView mTxtPayWith;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    //    @BindView(R.id.recycle_pay_bank)
//    RecyclerView mRecyclePayBank;
    @BindView(R.id.btn_click_choose_bank)
    Button mBtnClickChooseBank;
    @BindView(R.id.ab_img_button_back)
    ImageButton mAbImgButtonBack;
    @BindView(R.id.ab_txt_title_action_bar)
    TextView mAbTxtTitleActionBar;
    @BindView(R.id.ab_img_button_confirm)
    ImageView mAbImgButtonConfirm;
    @BindView(R.id.action_bar_app)
    RelativeLayout mActionBarApp;
    @BindView(R.id.my_progess_bar)
    RelativeLayout mMyProgessBar;
    @BindView(R.id.txt_title_type_card)
    TextView txtTitleTypeCard;
    @BindView(R.id.txt_type_card)
    TextView txtTypeCard;
    @BindView(R.id.txt_title_count)
    TextView txtTitleCount;
    @BindView(R.id.txt_count_card)
    TextView txtCountCard;
    @BindView(R.id.txt_title_price)
    TextView txtTitlePrice;
    @BindView(R.id.txt_price_card)
    TextView txtPriceCard;
    @BindView(R.id.rl_info_buy)
    RelativeLayout rlInfoBuy;
    private Context mContext;
    private ArrayList<BankType> bankTypes;
    //    private PayBankAdapter mPayBankAdapter;
    private PayBankGetDataForListIterator mIterator;
    //    private RecyclerView.LayoutManager mLayoutManager;
    private InfoUserEdit mUserEdit;
    private int fromFragment;
    private String token;
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bottom_sheet_pay_banl;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRbItemBanthe.setChecked(true);
        token = SharedPrefs.getInstance().get(ACCESS_TOKEN, String.class);
        Intent intent = getIntent();
        if (intent != null) {
            fromFragment = intent.getIntExtra(Constant.FROM_FRAGMENT, 0);
            mUserEdit = (InfoUserEdit) intent.getSerializableExtra(Constant.INFO_USER_EDIT);
            txtTypeCard.setText(mUserEdit.getTypeCard());
            txtCountCard.setText(mUserEdit.getCountBuy() + "");
            txtPriceCard.setText(mUserEdit.getPrice() + "");
        } else {
            mUserEdit = new InfoUserEdit();
        }
        bankTypes = new ArrayList<>();
        mIterator = new PayBankGetDataImplIterator();
        mIterator.initDataForPayBank(fromFragment, bankTypes);
//        mPayBankAdapter = new PayBankAdapter(this, bankTypes, this);
//        mLayoutManager = new GridLayoutManager(mContext, 2);
//        mRecyclePayBank.setHasFixedSize(true);
//        mRecyclePayBank.setLayoutManager(mLayoutManager);
//        mRecyclePayBank.setAdapter(mPayBankAdapter);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mAbTxtTitleActionBar.setText("Phương thức thanh toán");
        mActionBarApp.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_click_choose_bank)
    public void onViewClicked() {
        PayBankPresenter payBankPresenter = new PayBankPresenterImpl(this, this);
        if (mRbItemBanthe.isChecked()) {
            payBankPresenter.fromAccountDoithe(fromFragment, token, mUserEdit);
        } else {
            payBankPresenter.getUrlBank(PAY_CARD_FRAGMENT, token, mUserEdit, url);
        }
    }

    @Override
    public void onClickBank(int position) {
        mUserEdit.setBankCode(bankTypes.get(position).getBankName());
//        mPayBankAdapter.updateAll();
        bankTypes.get(position).setbIsClick(true);
        mRbItemBanthe.setChecked(false);
//        mPayBankAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.rl_ban_the)
    public void onViewClickedMuathe() {
//        mPayBankAdapter.updateAll();
//        mPayBankAdapter.notifyDataSetChanged();
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
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra(FROM_PAY_BANK, 1);
        intent.putExtra(URL_PAY_BANK, url);
        startActivity(intent);
    }

    @Override
    public void onGetListCardSuccess(ResultCardDoithe sListCard, String message) {
//        Log.e("card",sListCard);
//        Log.e("card",message);
        if (sListCard.getData() != null) {
            UserInfo mUserInfo = ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class));
            AutoLogin autoLogin = new AutoLogin(this);
            if (!autoLogin.bLogin) {
                autoLogin.login(mUserInfo.getPassWord());
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ResultDetailNoBankActivity.class);
            intent.putExtra(USER_INFO,mUserEdit);
            intent.putExtra(LIST_CARD, sListCard);
            startActivity(intent);
        } else {
            Toast.makeText(this, message + "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetMessagePayForMobile(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.ab_img_button_back, R.id.ab_img_button_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ab_img_button_back:
                super.onBackPressed();
                break;
            case R.id.ab_img_button_confirm:
                finish();
                break;
        }
    }
}
