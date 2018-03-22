package vn.doithe66.doithe66.presenter;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import java.util.ArrayList;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.model.ItemNav;
import vn.doithe66.doithe66.model.UserInfo;
import vn.doithe66.doithe66.view.HomeActivityView;
import vn.doithe66.doithe66.view.ProgessView;

/**
 * Created by Windows 10 Now on 1/9/2018.
 */

public class HomeActivityPresenterImpl
        implements HomeActivityPresenter, OnHomeGetDiscountFinishedListener {
    private HomeActivityView mHomeActivityView;
    private ProgessView mProgessView;
    private HomeIterator mHomeIterator;

    public HomeActivityPresenterImpl(HomeActivityView homeActivityView, ProgessView progessView) {
        mHomeActivityView = homeActivityView;
        mProgessView = progessView;
        mHomeIterator = new HomeIteratorImpl();
    }

    @Override
    public void onButtonNavClick(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(Gravity.LEFT, false);
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onLoadFragment(ItemNav itemNav) {
        mHomeActivityView.setTittleActionBar(itemNav.getTitle());
        mHomeActivityView.closeNav();
        mProgessView.showProgess();
    }

    @Override
    public void onLoadFragmentSuccess() {
        mProgessView.hideProgess();
    }

    @Override
    public void checkToken(String token ,ArrayList<ItemCard> itemCards) {
        mProgessView.showProgess();
        mHomeIterator.getDataFromToken(token, itemCards, this);
    }

    @Override
    public void onErrorToken() {
        mHomeActivityView.showErrorToken();
    }

    @Override
    public void onSuccess(ArrayList<ItemCard> itemCards) {
        mProgessView.hideProgess();
        mHomeActivityView.onSuccessGetData(itemCards);
    }

    @Override
    public void onSuccessUserInfo(UserInfo userInfo) {
        mProgessView.hideProgess();
        mHomeActivityView.onSuccessGetUserInfo(userInfo);
    }
}
