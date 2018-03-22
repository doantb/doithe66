package vn.doithe66.doithe66.presenter;

import android.support.v4.widget.DrawerLayout;
import java.util.ArrayList;

import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.model.ItemNav;

/**
 * Created by Windows 10 Now on 1/9/2018.
 */

public interface HomeActivityPresenter {
    public void onButtonNavClick(DrawerLayout drawerLayout);

    public void onLoadFragment(ItemNav itemNav);

    public void onLoadFragmentSuccess();

    public void checkToken(String token, ArrayList<ItemCard> itemCards);
}
