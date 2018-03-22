package vn.doithe66.doithe66.presenter;

import java.util.ArrayList;

import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.ItemMenu;

/**
 * Created by Administrator on 3/21/2018.
 */

public class HomeFrmIteratorImpl {
    private HomeFrmIterator homeFrmIterator;
    private ArrayList<ItemMenu> itemMenus;

    public HomeFrmIteratorImpl(ArrayList<ItemMenu> itemMenus) {
        this.itemMenus = itemMenus;
        genarateItemMenuImpl();
    }

    public void setHomeFrmIterator(HomeFrmIterator homeFrmIterator) {
        this.homeFrmIterator = homeFrmIterator;
    }

    public void genarateItemMenuImpl() {
        itemMenus = new ArrayList<>();
        itemMenus.add(new ItemMenu(R.drawable.atm_48px, "Đổi thẻ cào thành tiền"));
        itemMenus.add(new ItemMenu(R.drawable.loyalty_card_48px, "Mua mã thẻ"));
        itemMenus.add(new ItemMenu(R.drawable.android_tablet_48px, "Nạp tiền điện thoại"));
        itemMenus.add(new ItemMenu(R.drawable.clock_48px, "Lịch sử giao dịch"));
        itemMenus.add(new ItemMenu(R.drawable.rent_48px, "Tài khoản ngân hàng"));
        itemMenus.add(new ItemMenu(R.drawable.online_money_transfer_48px, "Rút tiền"));
        itemMenus.add(new ItemMenu(R.drawable.money_48px, "Rút tiền"));
        itemMenus.add(new ItemMenu(R.drawable.more_info_48px, "Thông tin tài khoản"));
        itemMenus.add(new ItemMenu(R.drawable.rss_48px, "Tin tức"));
        homeFrmIterator.genarateItemMenu(itemMenus);
    }
}
