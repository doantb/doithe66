package vn.doithe66.doithe66.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.LoginActivity;
import vn.doithe66.doithe66.NotificationActivity;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.Utils.Utils;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.fragment.HistoryTransactionFragment;
import vn.doithe66.doithe66.fragment.InfoBankFragment;
import vn.doithe66.doithe66.fragment.MakeMoneyFragment;
import vn.doithe66.doithe66.fragment.PayCardFragment;
import vn.doithe66.doithe66.fragment.PayFastFragment;
import vn.doithe66.doithe66.fragment.ProfileFragment;
import vn.doithe66.doithe66.fragment.TransferFragment;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.model.RegisterMDResult;
import vn.doithe66.doithe66.model.UserInfo;

import static vn.doithe66.doithe66.Utils.Constant.ITEM_BANK_ACCOUNT;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_EXCHANGE_CARD;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_HISTORY;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_INFO_ACCOUNT;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_MAKE_MONEY;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_NEWS;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_PAY_CARD;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_PAY_PHONE;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_TRANSFER_MONEY;
import static vn.doithe66.doithe66.Utils.Constant.LIST_CARD;
import static vn.doithe66.doithe66.Utils.Constant.PUT_CODE_TRANS;
import static vn.doithe66.doithe66.Utils.Constant.RESPONE_TRANSFER;

/**
 * Created by Administrator on 3/23/2018.
 */

public class GenaralActivity extends BaseActivity {
    @BindView(R.id.ab_img_button_back)
    ImageButton abImgButtonBack;
    @BindView(R.id.ab_txt_title_action_bar)
    TextView abTxtTitleActionBar;
    @BindView(R.id.ab_img_button_confirm)
    ImageView abImgButtonConfirm;
    @BindView(R.id.action_bar_app)
    RelativeLayout actionBarApp;
    @BindView(R.id.frm_genaral)
    FrameLayout frmGenaral;
    @BindView(R.id.txt_notifi_transfer)
    TextView txtNotifiTransfer;
    @BindView(R.id.txt_email_get_code)
    TextView txtGotoEmail;
    @BindView(R.id.edt_code_trans)
    EditText edtCodeTrans;
    @BindView(R.id.frm_tran_money_ln_count)
    LinearLayout frmTranMoneyLnCount;
    @BindView(R.id.frm_tran_money_btn_trans_money)
    Button frmTranMoneyBtnTransMoney;
    @BindView(R.id.ln_trans)
    LinearLayout lnTrans;

    private ArrayList<ItemCard> itemCards;
    private Intent intent;
    private UserInfo userInfo;
    private String token;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_genaral;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        itemCards = new ArrayList<>();
        intent = getIntent();
        if (intent.getStringExtra(Constant.FROM_TRANSFER_MONEY) != null && intent.getStringExtra(Constant.FROM_TRANSFER_MONEY).equalsIgnoreCase(PUT_CODE_TRANS)) {
            RegisterMDResult registerMDResult = intent.getParcelableExtra(RESPONE_TRANSFER);
            lnTrans.setVisibility(View.VISIBLE);
            txtNotifiTransfer.setText(registerMDResult.getMessage() + ", Bạn cần vào email để lấy mã code xác nhận chuyển tiền và nhập vào bên dưới");
            abTxtTitleActionBar.setText("Xác thực chuyển tiền");
        }
        userInfo = ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class));
        token = SharedPrefs.getInstance().get(Constant.ACCESS_TOKEN, String.class);
        switchItemMenu(intent.getIntExtra(Constant.FROM_ITEM, 0));
        setCancel(abImgButtonConfirm);
        setBack(abImgButtonBack);
        hiddenInputType();
    }

    private void switchItemMenu(int intExtra) {
        switch (intExtra) {
            case ITEM_EXCHANGE_CARD:
                break;
            case ITEM_PAY_CARD:
                abTxtTitleActionBar.setText("Mua mã thẻ");
                itemCards = intent.getParcelableArrayListExtra(LIST_CARD);
                loadFragment(PayCardFragment.newInstance(token, itemCards));
                break;
            case ITEM_PAY_PHONE:
                abTxtTitleActionBar.setText("Nạp tiền điện thoại");
                loadFragment(PayFastFragment.newInstance(token));
                break;
            case ITEM_HISTORY:
                abTxtTitleActionBar.setText("Lịch sử giao dịch");
                loadFragment(HistoryTransactionFragment.newInstance(token));
                break;
            case ITEM_BANK_ACCOUNT:
                abTxtTitleActionBar.setText("Tài khoản ngân hàng");
                UserInfo mUserInfo = ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class));
                if (mUserInfo != null) {
                    loadFragment(InfoBankFragment.newInstance(mUserInfo));
                } else {
                    Toast.makeText(this, "Phiên đăng nhập hết hạn,Bạn cần đăng nhập lại",
                            Toast.LENGTH_SHORT).show();
                    startActivity(LoginActivity.class);
                }
                break;
            case ITEM_MAKE_MONEY:
                abTxtTitleActionBar.setText("Rút tiền");
                loadFragment(MakeMoneyFragment.newInstance(token));
                break;
            case ITEM_TRANSFER_MONEY:
                abTxtTitleActionBar.setText("Chuyển tiền");
                loadFragment(TransferFragment.newInstance(token));
                break;
            case ITEM_INFO_ACCOUNT:
                abTxtTitleActionBar.setText("Thông tin tài khoản");
                loadFragment(ProfileFragment.newInstance(userInfo));
                break;
            case ITEM_NEWS:
                abTxtTitleActionBar.setText("Tin tức");
                startActivity(NotificationActivity.class);
                break;
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frm_genaral, fragment, Fragment.class.getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBackPressedCustom();
    }

    @OnClick({R.id.txt_email_get_code, R.id.frm_tran_money_btn_trans_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_email_get_code:
//                PackageManager manager = getPackageManager();
//                Intent i = manager.getLaunchIntentForPackage("com.google.android.gmail");
//                i.addCategory(Intent.CATEGORY_LAUNCHER);
//                startActivity(i);
//                Intent mailClient = new Intent(Intent.ACTION_VIEW);
//                mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
//                startActivity(mailClient);
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                startActivity(intent);
                break;
            case R.id.frm_tran_money_btn_trans_money:
                Utils.closeKeyboard(this,edtCodeTrans.getWindowToken());
                if (edtCodeTrans.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Bạn chưa nhập mã code giao dịch", Toast.LENGTH_SHORT).show();
                } else {
                    Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
                    retrofit.create(InterfaceAPI.class).transferMoneyAuthenticate(edtCodeTrans.getText().toString()).enqueue(new Callback<RegisterMDResult>() {
                        @Override
                        public void onResponse(Call<RegisterMDResult> call, Response<RegisterMDResult> response) {
                            if (response.body() != null) {
                                Toast.makeText(GenaralActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                finishWithAnim();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterMDResult> call, Throwable t) {

                        }
                    });
                }
                break;
        }
    }
}
