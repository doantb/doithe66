package vn.doithe66.doithe66.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import java.text.ParseException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.doithe66.doithe66.LoginActivity;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.adapter.NavigationAdapter;
import vn.doithe66.doithe66.model.ItemNav;

/**
 * Created by Windows 10 Now on 1/3/2018.
 */

public class NavigationFragment extends BaseFragment implements NavigationAdapter.clickItem {
    @BindView(R.id.register_img)
    ImageView mRegisterImg;
    @BindView(R.id.rcl_nav)
    RecyclerView mRclNav;
    @BindView(R.id.nav_txt_logout)
    TextView mNavTxtLogout;
    @BindView(R.id.nav_txt_user_name)
    TextView mNavTxtUserName;
    @BindView(R.id.img_avatar_profile_user)
    CircleImageView mImgAvatarProfileUser;
    Unbinder unbinder;
    private RecyclerView rclNav;
    private ArrayList<ItemNav> mItemNavs;
    private NavigationAdapter mNavigationAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private onItemNavClickListener mListener;

    public void setListener(onItemNavClickListener listener) {
        mListener = listener;
    }

    @Override
    protected int layoutId() {
        return R.layout.nav_fragment;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        rclNav = mRoot.findViewById(R.id.rcl_nav);
        initData();
        if (SharedPrefs.getInstance().get(Constant.USER_NAME, String.class) != null) {
            mNavTxtUserName.setText(
                    SharedPrefs.getInstance().get(Constant.USER_NAME, String.class));
        }
        mNavigationAdapter = new NavigationAdapter(mItemNavs, getActivity());
        mNavigationAdapter.setClickItem(this);
        mLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rclNav.setLayoutManager(mLayoutManager);
        rclNav.setAdapter(mNavigationAdapter);
    }

    @OnClick(R.id.nav_txt_logout)
    public void onViewClicked() {
        removeAccessToken();
        saveEmail();
        saveName();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra(LoginActivity.RC_SIGN_OUT, 1);
        startActivity(intent);
    }

    private void initData() {
        mItemNavs = new ArrayList<>();
        mItemNavs.add(new ItemNav(R.drawable.ic_home, "Trang chủ"));
        mItemNavs.add(new ItemNav(R.drawable.ic_discount, "Chiết khấu"));
        mItemNavs.add(new ItemNav(R.drawable.ic_user, "Thông tin tài khoản"));
        mItemNavs.add(new ItemNav(R.drawable.ic_history, "Lịch sử giao dịch"));
        mItemNavs.add(new ItemNav(R.drawable.ic_bank, "Ngân hàng"));
        mItemNavs.add(new ItemNav(R.drawable.ic_contact, "Liên hệ"));
    }

    @Override
    public void onClickItemNav(View view, int position) {
        ItemNav itemNav = mItemNavs.get(position);
        mListener.onClickItemNav(itemNav, position);
    }

    public interface onItemNavClickListener {
        public void onClickItemNav(ItemNav itemNav, int position);
    }

    private void removeAccessToken() {
        SharedPrefs.getInstance().put(Constant.ACCESS_TOKEN, "");
    }

    private void saveEmail() {
        SharedPrefs.getInstance().put(Constant.EMAIL, "");
    }

    private void saveName() {
        SharedPrefs.getInstance().put(Constant.USER_NAME, "");
    }
}
