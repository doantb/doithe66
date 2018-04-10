package vn.doithe66.doithe66.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.LoginActivity;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.AutoLogin;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.Utils.Utils;
import vn.doithe66.doithe66.activity.GenaralActivity;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.RegisterMDResult;
import vn.doithe66.doithe66.model.UserInfo;

import static vn.doithe66.doithe66.Utils.Constant.KEY_TOKEN;
import static vn.doithe66.doithe66.Utils.Constant.LIST_CARD;

public class TransferFragment extends BaseFragment {

    @BindView(R.id.frm_tran_money_txt_name_own_account)
    TextView frmTranMoneyTxtNameOwnAccount;
    @BindView(R.id.frm_tran_money_txt_money)
    TextView frmTranMoneyTxtMoney;
    @BindView(R.id.frm_tran_money_txt_name_bank)
    TextView frmTranMoneyTxtNameBank;
    @BindView(R.id.frm_tran_money_edt_email)
    EditText frmTranMoneyEdtEmail;
    @BindView(R.id.frm_tran_btn_search_info_receiver)
    Button frmTranBtnSearchInfoReceiver;
    @BindView(R.id.frm_tran_money_txt_name_receiver)
    TextView frmTranMoneyTxtNameReceiver;
    @BindView(R.id.frm_tran_money_txt_number_bank)
    TextView frmTranMoneyTxtNumberBank;
    @BindView(R.id.frm_tran_money_txt_name_bank_receiver)
    TextView frmTranMoneyTxtNameBankReceiver;
    @BindView(R.id.frm_tran_money_ln_receiver)
    LinearLayout frmTranMoneyLnReceiver;
    @BindView(R.id.frm_tran_money_btn_trans_money)
    Button frmTranMoneyBtnTransMoney;

    private static boolean isClick = false;
    @BindView(R.id.frm_tran_money_txt_end_less)
    TextView frmTranMoneyTxtEndLess;
    @BindView(R.id.frm_tran_money_edt_count)
    EditText frmTranMoneyEdtCount;
    @BindView(R.id.frm_tran_money_ln_count)
    LinearLayout frmTranMoneyLnCount;
    @BindView(R.id.my_progess_bar)
    RelativeLayout myProgessBar;

    private String token;

    public static TransferFragment newInstance(String token) {
        TransferFragment transferFragment = new TransferFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TOKEN, token);
        transferFragment.setArguments(args);
        return transferFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_transfer_money;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        myProgessBar.setVisibility(View.VISIBLE);
        token = getArguments().getString(KEY_TOKEN);
        if (token != null) {
            Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
            retrofit.create(InterfaceAPI.class).getUserInfo().enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    if (response.body() != null) {
                        if (response.body().getName() == null) {
//                            if (ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class)) != null) {
                            UserInfo mUserInfo = ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class));
                            AutoLogin autoLogin = new AutoLogin(getActivity());
                            if (!autoLogin.bLogin) {
                                autoLogin.login(mUserInfo.getPassWord());
                            }
//                            }else {
//                                Intent intent = new Intent(getActivity(),LoginActivity.class);
//                                startActivity(intent);
//                            }
                        } else {
                            frmTranMoneyTxtNameOwnAccount.setText(response.body().getBankAccount());
                            frmTranMoneyTxtNameBank.setText(response.body().getBankName());
                            frmTranMoneyTxtMoney.setText("Số dư  : " + response.body().getSoDuKhaDung());
                        }
                    }
                    myProgessBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {

                }
            });
        }
    }

    @OnClick({R.id.frm_tran_btn_search_info_receiver, R.id.frm_tran_money_btn_trans_money, R.id.frm_tran_money_txt_end_less})
    public void onViewClicked(View view) {
        myProgessBar.setVisibility(View.VISIBLE);
        String email = frmTranMoneyEdtEmail.getText().toString();
        String count = frmTranMoneyEdtCount.getText().toString();
        switch (view.getId()) {
            case R.id.frm_tran_btn_search_info_receiver:
                Utils.closeKeyboard(getContext(), frmTranMoneyEdtEmail.getWindowToken());
                if (!isClick) {
                    Animation aSlideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
                    frmTranMoneyLnReceiver.startAnimation(aSlideDown);
                    frmTranMoneyLnReceiver.setVisibility(View.VISIBLE);
                    frmTranMoneyBtnTransMoney.setVisibility(View.VISIBLE);
                    frmTranMoneyLnCount.setVisibility(View.VISIBLE);
                    isClick = true;
                }
                if (email.isEmpty()) {
                    frmTranMoneyEdtEmail.setError("Bạn chưa nhập email nhận tiền");
                } else {
                    Retrofit retrofit = ConfigRetrofitApi.getInstance();
                    retrofit.create(InterfaceAPI.class).getInfoAccountReceiver(email).enqueue(new Callback<UserInfo>() {
                        @Override
                        public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                            if (response.body() != null) {
                                frmTranMoneyTxtNameReceiver.setText(response.body().getBankAccount());
                                frmTranMoneyTxtNameBankReceiver.setText(response.body().getBankName());
                                frmTranMoneyTxtNumberBank.setText("Số tài khoản  : " + response.body().getBankNumber());
                            }
                            myProgessBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<UserInfo> call, Throwable t) {

                        }
                    });
                }
                break;
            case R.id.frm_tran_money_btn_trans_money:
                Utils.closeKeyboard(getContext(), frmTranMoneyEdtCount.getWindowToken());
                if (email.isEmpty()) {
                    frmTranMoneyEdtEmail.setError("Bạn chưa nhập email nhận tiền");
                } else if (count.isEmpty()) {
                    frmTranMoneyEdtCount.setError("Bạn chưa nhập số tiền cần chuyển");
                } else {
                    Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
                    retrofit.create(InterfaceAPI.class).transferMoney(count, email).enqueue(new Callback<RegisterMDResult>() {
                        @Override
                        public void onResponse(Call<RegisterMDResult> call, Response<RegisterMDResult> response) {
                            if (response.body() != null) {
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), GenaralActivity.class);
                                intent.putExtra(Constant.FROM_TRANSFER_MONEY, Constant.PUT_CODE_TRANS);
                                intent.putExtra(Constant.RESPONE_TRANSFER, response.body());
                                startActivity(intent);
                                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                            }
                            myProgessBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<RegisterMDResult> call, Throwable t) {

                        }
                    });
                }
                break;
            case R.id.frm_tran_money_txt_end_less:
                isClick = false;
                frmTranMoneyLnReceiver.setVisibility(View.GONE);
                myProgessBar.setVisibility(View.GONE);
                break;
        }
    }


    @OnClick()
    public void onViewClicked() {
    }

    @Override
    public void onResume() {
        super.onResume();
        isClick = false;
    }
}
