package vn.doithe66.doithe66.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vn.doithe66.doithe66.HomeActivity;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.AutoLogin;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.adapter.RvCardBuySuccessAdapter;
import vn.doithe66.doithe66.model.InfoUserEdit;
import vn.doithe66.doithe66.model.ResultCard;
import vn.doithe66.doithe66.model.ResultCardDoithe;
import vn.doithe66.doithe66.model.UserInfo;

/**
 * Created by Windows 10 Now on 1/20/2018.
 */

public class ResultDetailNoBankActivity extends BaseActivity
        implements RvCardBuySuccessAdapter.onClickCopy {
    @BindView(R.id.tv_number_card_success)
    TextView tvNumberCardSuccess;
    @BindView(R.id.tv_type_card_success)
    TextView tvTypeCardSuccess;
    @BindView(R.id.tv_email_check_success)
    TextView tvEmailCheckSuccess;
    @BindView(R.id.tv_price_success)
    TextView tvPriceSuccess;
    @BindView(R.id.btn_ok_success)
    Button btnOkSuccess;

    @BindView(R.id.recycle_seri_card_done)
    RecyclerView rvCardSuccess;
    @BindView(R.id.txt_thank_you)
    TextView mTxtThankYou;

    private InfoUserEdit infoUserEdit;
    private RvCardBuySuccessAdapter rvCardBuySuccessAdapter;
    private ArrayList<ResultCardDoithe.DataBean> lisCardSuccess;
    private ResultCardDoithe resultCardDoithe;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_result_detail_no_bank;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            infoUserEdit = (InfoUserEdit) intent.getSerializableExtra(Constant.USER_INFO);
            resultCardDoithe = intent.getParcelableExtra(Constant.LIST_CARD);
        } else {
            infoUserEdit = null;
        }
        initInfor();
        initRecycleView();
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
    }

    private void initRecycleView() {
        lisCardSuccess = new ArrayList<>();
        lisCardSuccess = (ArrayList<ResultCardDoithe.DataBean>) resultCardDoithe.getData();
//        addListResultCard(dataBeans);
        // lay du lieu cho lisCardSuccess
        rvCardBuySuccessAdapter = new RvCardBuySuccessAdapter(this, lisCardSuccess);
        rvCardSuccess.setAdapter(rvCardBuySuccessAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCardSuccess.setLayoutManager(layoutManager);
        rvCardBuySuccessAdapter.notifyDataSetChanged();
        rvCardBuySuccessAdapter.setOnClickCopy(this);
    }

    private void initInfor() {
        tvNumberCardSuccess.setText("" + infoUserEdit.getCountBuy() + " thẻ");
        tvTypeCardSuccess.setText(infoUserEdit.getTypeCard());
        tvEmailCheckSuccess.setText(infoUserEdit.getEmail());
        tvPriceSuccess.setText(infoUserEdit.getPrice());
        if (infoUserEdit.getCountBuy() > 3) {
            mTxtThankYou.setText(
                    "Quý khách cũng có thể check Email để xem lại mã thẻ. Rất hân hạnh được phục vụ quý khách!");
        }
    }

    @OnClick({R.id.btn_ok_success})
    public void onClickButterKnife(View view) {
        switch (view.getId()) {
            case R.id.btn_ok_success:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }

//    private void addListResultCard(String s) {
//        try {
//            JSONArray jsonArray = new JSONArray(s);
//            lisCardSuccess = new ArrayList<>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject explrObject = jsonArray.getJSONObject(i);
//                ResultCard resultCard = new ResultCard();
//                resultCard.setProviderCode(explrObject.optString("PinCode"));
//                resultCard.setSerial(explrObject.optString("Serial"));
//                lisCardSuccess.add(resultCard);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onCopy(int position) {
        ResultCardDoithe.DataBean resultCard = lisCardSuccess.get(position);
        setClipBoard(resultCard.getProviderCode());
    }

    private void setClipBoard(String textCopy) {
        ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Cliped board", textCopy);
        manager.setPrimaryClip(clipData);
    }

}
