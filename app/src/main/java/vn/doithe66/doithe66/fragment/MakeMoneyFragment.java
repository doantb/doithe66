package vn.doithe66.doithe66.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.Utils;
import vn.doithe66.doithe66.config.ConfigRetrofitApi;
import vn.doithe66.doithe66.config.InterfaceAPI;
import vn.doithe66.doithe66.model.RegisterMDResult;

import static vn.doithe66.doithe66.Utils.Constant.KEY_TOKEN;

public class MakeMoneyFragment extends BaseFragment {
    @BindView(R.id.frm_make_money_edt_money)
    EditText frmMakeMoneyEdtMoney;
    @BindView(R.id.frm_make_money_edt_passlv2)
    EditText frmMakeMoneyEdtPasslv2;
    @BindView(R.id.frm_make_money_edt_note)
    EditText frmMakeMoneyEdtNote;
    @BindView(R.id.frm_make_money_btn_make_money)
    Button frmMakeMoneyBtnMakeMoney;

    private String token;

    public static MakeMoneyFragment newInstance(String token) {
        MakeMoneyFragment makeMoneyFragment = new MakeMoneyFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TOKEN, token);
        makeMoneyFragment.setArguments(args);
        return makeMoneyFragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_make_money;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        token = getArguments().getString(KEY_TOKEN);
    }

    @OnClick(R.id.frm_make_money_btn_make_money)
    public void onViewClicked() {
        Utils.closeKeyboard(getContext(),frmMakeMoneyEdtPasslv2.getWindowToken());
        String money = frmMakeMoneyEdtMoney.getText().toString();
        String passLv2 = frmMakeMoneyEdtPasslv2.getText().toString();
        if (checkInput(money, passLv2)) {
            Retrofit retrofit = ConfigRetrofitApi.getInstance(token);
            retrofit.create(InterfaceAPI.class).makeMoney(money, passLv2).enqueue(new Callback<RegisterMDResult>() {
                @Override
                public void onResponse(Call<RegisterMDResult> call, Response<RegisterMDResult> response) {
                    if (response.body() != null) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterMDResult> call, Throwable t) {

                }
            });
        }
    }

    private boolean checkInput(String money, String passLv2) {
        if (money.isEmpty()) {
            frmMakeMoneyEdtMoney.setError("Bạn chưa nhập số tiền cần rút");
            return false;
        } else if (passLv2.isEmpty()) {
            frmMakeMoneyEdtPasslv2.setError("Bạn chưa nhập mật khẩu cấp 2");
            return false;
        }
        return true;
    }
}
