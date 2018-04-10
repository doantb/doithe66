package vn.doithe66.doithe66;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;

import java.util.ArrayList;

import vn.doithe66.doithe66.Utils.AutoLogin;
import vn.doithe66.doithe66.Utils.CheckInternet;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.Utils.Utils;
import vn.doithe66.doithe66.activity.BaseActivity;
import vn.doithe66.doithe66.fragment.ContactFragment;
import vn.doithe66.doithe66.fragment.DiscountFragment;
import vn.doithe66.doithe66.fragment.HistoryTransactionFragment;
import vn.doithe66.doithe66.fragment.HomeFragment;
import vn.doithe66.doithe66.fragment.InfoBankFragment;
import vn.doithe66.doithe66.fragment.NavigationFragment;
import vn.doithe66.doithe66.fragment.ProfileFragment;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.model.ItemNav;
import vn.doithe66.doithe66.model.UserInfo;
//import vn.doithe66.doithe66.networking.TaskGetLogin;
import vn.doithe66.doithe66.presenter.HomeActivityPresenter;
import vn.doithe66.doithe66.presenter.HomeActivityPresenterImpl;
import vn.doithe66.doithe66.view.HomeActivityView;
import vn.doithe66.doithe66.view.ProgessView;

/**
 * Created by Windows 10 Now on 1/3/2018.
 */

public class HomeActivity extends BaseActivity
        implements HomeActivityView, NavigationFragment.onItemNavClickListener, ProgessView {
    private static final int ANIM_DURATION_TOOLBAR = 300;
    @BindView(R.id.home_txt_title_action_bar)
    TextView mHomeTxtTitleActionBar;
    @BindView(R.id.action_bar_home)
    RelativeLayout mActionBarHome;
    @BindView(R.id.fr_main)
    FrameLayout mFrMain;
    @BindView(R.id.home_img_button_menu)
    ImageButton mHomeImgButtonMenu;
    @BindView(R.id.home_img_logo_action_bar)
    ImageView mHomeImgLogoActionBar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.home_img_notification)
    ImageView mHomeImgNotification;
    @BindView(R.id.my_progess_bar)
    RelativeLayout mMyProgessBar;
    @BindView(R.id.home_txt_logout)
    TextView mHomeTxtLogout;
    private HomeActivityPresenter mHomeActivityPresenter;
    private String token;
    private ArrayList<ItemCard> mItemCards;
    private UserInfo mUserInfo;
    //    private Stack<String> mStringStack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (!CheckInternet.haveNetworkConnection(this)) {
            showDialogInternet();
        }
        token = SharedPrefs.getInstance().get(Constant.ACCESS_TOKEN, String.class);
        if (ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class)) != null) {
            mUserInfo = ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class));
            AutoLogin autoLogin = new AutoLogin(this);
            if (!autoLogin.bLogin) {
                autoLogin.login(mUserInfo.getPassWord());
            }
        } else {
            startActivity(LoginActivity.class);
        }
        startIntroAnimation();
        mHomeActivityPresenter = new HomeActivityPresenterImpl(this, this);
        mHomeActivityPresenter.checkToken(token, mItemCards);
        mHomeImgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(NotificationActivity.class);
            }
        });
        NavigationFragment mFragmentNavigation =
                (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.frm_nav);
        mFragmentNavigation.setListener(this);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
//        checkUpdate();
        //        mStringStack = new Stack<>();
    }

    private void showDialogInternet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Đổi thẻ 66 !")
                .setCancelable(false)
                .setMessage("Vui lòng kiểm tra kết nối Wifi hoặc 3G")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .show();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fr_main, fragment, Fragment.class.getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        mHomeActivityPresenter.onLoadFragmentSuccess();
    }

    private void startIntroAnimation() {
        int actionbarSize = Utils.dpToPx(56);
        mActionBarHome.setTranslationY(-actionbarSize);
        mHomeImgButtonMenu.setTranslationY(-actionbarSize);
        mHomeImgLogoActionBar.setTranslationY(-actionbarSize);
        mActionBarHome.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        mHomeImgButtonMenu.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400);
        mHomeImgLogoActionBar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }
                })
                .start();
    }

    @OnClick(R.id.home_img_button_menu)
    public void onViewClicked() {
        mHomeActivityPresenter.onButtonNavClick(mDrawerLayout);
    }

    @Override
    public void openNav() {
        mDrawerLayout.openDrawer(Gravity.LEFT, false);
    }

    @Override
    public void closeNav() {
        mDrawerLayout.closeDrawer(Gravity.LEFT, false);
    }

    @Override
    public void showNavFragment() {

    }

    @Override
    public void disPlayListCard() {

    }

    @Override
    public void showErrorToken() {
        mHomeImgNotification.setVisibility(View.GONE);
        mHomeTxtLogout.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Phiên đăng nhập hết hạn,Bạn cần đăng nhập lại", Toast.LENGTH_LONG)
                .show();
        token = null;
    }

    @Override
    public void showNodata() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void setTittleActionBar(String title) {
        mHomeTxtTitleActionBar.setText(title);
        mHomeTxtTitleActionBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessGetData(ArrayList<ItemCard> itemCards) {
        //        mHomeTxtLogout.setVisibility(View.GONE);
        //        mHomeImgNotification.setVisibility(View.VISIBLE);
        mItemCards = new ArrayList<>();
        mItemCards.addAll(itemCards);
        HomeFragment homeFragment = HomeFragment.newInstance(token, itemCards, mUserInfo);
        loadFragment(homeFragment);
        //        mStringStack.push("Trang chủ");
    }

    @Override
    public void onSuccessGetUserInfo(UserInfo userInfo) {
        mHomeTxtLogout.setVisibility(View.GONE);
        mHomeImgNotification.setVisibility(View.VISIBLE);
        mUserInfo = userInfo;
    }

    @Override
    public void onClickItemNav(ItemNav itemNav, int position) {
        //        mStringStack.push(itemNav.getTitle());
        mHomeActivityPresenter.onLoadFragment(itemNav);
        switch (position) {
            case 0:
                mHomeImgNotification.setVisibility(View.VISIBLE);
                loadFragment(HomeFragment.newInstance("", mItemCards, mUserInfo));
                break;
            case 1:
                mHomeActivityPresenter.onLoadFragmentSuccess();
                mHomeImgNotification.setVisibility(View.GONE);
                loadFragment(DiscountFragment.newInstance(mItemCards));
                break;
            case 2:
                if (mUserInfo != null) {
                    mHomeImgNotification.setVisibility(View.GONE);
                    loadFragment(ProfileFragment.newInstance(mUserInfo));
                } else {
                    Toast.makeText(this, "Phiên đăng nhập hết hạn,Bạn cần đăng nhập lại",
                            Toast.LENGTH_SHORT).show();
                    startActivity(LoginActivity.class);
                }
                break;
            case 3:
                if (token != null) {
                    mHomeImgNotification.setVisibility(View.GONE);
                    loadFragment(HistoryTransactionFragment.newInstance(token));
                } else {
                    Toast.makeText(this, "Phiên đăng nhập hết hạn,Bạn cần đăng nhập lại",
                            Toast.LENGTH_SHORT).show();
                    startActivity(LoginActivity.class);
                }
                break;
            case 4:
                if (mUserInfo != null) {
                    mHomeImgNotification.setVisibility(View.GONE);
                    loadFragment(InfoBankFragment.newInstance(mUserInfo));
                } else {
                    Toast.makeText(this, "Phiên đăng nhập hết hạn,Bạn cần đăng nhập lại",
                            Toast.LENGTH_SHORT).show();
                    startActivity(LoginActivity.class);
                }
                break;
            case 5:
                mHomeImgNotification.setVisibility(View.GONE);
                loadFragment(ContactFragment.newInstance(""));
                break;
        }
        mHomeActivityPresenter.onLoadFragmentSuccess();
        mHomeImgLogoActionBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgess() {
        mMyProgessBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgess() {
        mMyProgessBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.home_txt_logout)
    public void onViewClickedLogIn() {
        startActivity(LoginActivity.class);
    }

    //    @Override
    //    protected void onRestart() {
    //        super.onRestart();
    //        mHomeActivityPresenter = new HomeActivityPresenterImpl(this, this);
    //        mHomeActivityPresenter.checkToken(token, mItemCards);
    //    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() < 3) {
            setDialogQuit();
        } else {
            super.onBackPressed();
            //            if (mStringStack.size() > 1) {
            //                if (mStringStack.get(mStringStack.size() - 1) != null) {
            //                    mStringStack.remove(mStringStack.size() - 1);
            //                    mHomeTxtTitleActionBar.setText(mStringStack.get(mStringStack.size() - 1));
            //                }
            //            }
        }
    }

    public void setDialogQuit() {
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(this);
        dialog.setTitle("Đổi thẻ 66");
        dialog.setMessage("Bạn có muốn thoát ứng dụng");
        dialog.setCancelable(true);
        dialog.setPositiveButton("Quay lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        dialog.setNegativeButton("Thoát app", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        android.app.AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private void checkUpdate() {
//        TaskGetLogin taskGetVersion = new TaskGetLogin(this.getPackageName()) {
//            @Override
//            protected void onPostExecute(String onlineVersion) {
//                super.onPostExecute(onlineVersion);
//                if (onlineVersion != null && !onlineVersion.isEmpty()) {
//                    String currentVersion = null;
//                    try {
//                        currentVersion =
//                                getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//                    } catch (PackageManager.NameNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
//
//                        //show dialog
//                        setDialogUpdate();
//                    }
//                }
//            }
//        };
//        taskGetVersion.execute();
    }

    private void setDialogUpdate() {
        AppUpdater appUpdater = new AppUpdater(this).setDisplay(Display.DIALOG)
                .setTitleOnUpdateAvailable("Đã có bản mới")
                .setContentOnUpdateAvailable("Cập nhật bản mới với tính năng mới");
        appUpdater.start();
    }
}
