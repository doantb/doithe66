package vn.doithe66.doithe66.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;

import java.text.ParseException;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.UserInfo;

import static vn.doithe66.doithe66.Utils.Constant.USER_INFO;

/**
 * Created by Windows 10 Now on 1/19/2018.
 */

public class InfoBankFragment extends BaseFragment {
    @BindView(R.id.frm_bank_name)
    EditText mFrmBankName;
    @BindView(R.id.frm_bank_phone)
    EditText mFrmBankPhone;
    @BindView(R.id.frm_bank_btn_edit_info)
    Button mFrmBankBtnEditInfo;
    private UserInfo mUserInfo;

    public static InfoBankFragment newInstance(UserInfo userInfo) {
        InfoBankFragment infoBankFragment = new InfoBankFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER_INFO, userInfo);
        infoBankFragment.setArguments(args);
        return infoBankFragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_info_bank;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUserInfo = (UserInfo) bundle.getSerializable(USER_INFO);
        }
        mFrmBankName.setText(mUserInfo.getName());
        mFrmBankPhone.setText(mUserInfo.getPhone());
    }

    @OnClick(R.id.frm_bank_btn_edit_info)
    public void onViewClicked() {
        Toast.makeText(getActivity(), "Chức năng này hiện đang phát triển", Toast.LENGTH_SHORT)
                .show();
        return;
    }
}
