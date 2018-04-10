package vn.doithe66.doithe66.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.activity.CreateNewPassActivity;
import vn.doithe66.doithe66.model.UserInfo;

import static vn.doithe66.doithe66.Utils.Constant.USER_INFO;

/**
 * Created by Windows 10 Now on 1/18/2018.
 */

public class ProfileFragment extends BaseFragment {
    @BindView(R.id.frm_profile_phone)
    TextView mFrmProfilePhone;
    @BindView(R.id.frm_profile_email)
    TextView mFrmProfileEmail;
    @BindView(R.id.frm_profile_edit_info)
    Button mFrmProfileEditInfo;
    @BindView(R.id.txt_user_profile_name)
    TextView mTxtUserProfileName;
    @BindView(R.id.txt_surplus)
    TextView mTxtSurplus;
    @BindView(R.id.txt_create_date)
    TextView mTxtCreateDate;
    private UserInfo mUserInfo;

    public static ProfileFragment newInstance(UserInfo userInfo) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(USER_INFO, userInfo);
        profileFragment.setArguments(args);
        return profileFragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUserInfo = (UserInfo) bundle.getParcelable(USER_INFO);
        }
        mTxtSurplus.setText(mUserInfo.getSoDuKhaDung()+ " " + "đ");
        mTxtCreateDate.setText(makeDate(mUserInfo.getCreateDate()));
        mTxtUserProfileName.setText(mUserInfo.getName());
        mFrmProfileEmail.setText(mUserInfo.getUserName());
        mFrmProfilePhone.setText(mUserInfo.getPhone());
    }

    @OnClick(R.id.frm_profile_edit_info)
    public void onViewClicked() {
        ForgetPassFragmentDialog forgetPassFragmentDialog = ForgetPassFragmentDialog.newInstance(Constant.PROFILE_ACTIVITY);
        forgetPassFragmentDialog.show(getActivity().getSupportFragmentManager(),
                "forgetPassFragmentDialog");
        forgetPassFragmentDialog.setCancelable(true);
    }
    private String makeDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = dateFormat.parse(date.substring(0, 10));
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            return format.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
