package vn.doithe66.doithe66.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.AutoLogin;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.activity.GenaralActivity;
import vn.doithe66.doithe66.adapter.ItemMenuAdapter;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.model.ItemMenu;
import vn.doithe66.doithe66.model.UserInfo;
import vn.doithe66.doithe66.presenter.HomeFrmIterator;
import vn.doithe66.doithe66.presenter.HomeFrmIteratorImpl;
import vn.doithe66.doithe66.presenter.HomeFrmPresenter;

import static vn.doithe66.doithe66.Utils.Constant.FROM_ITEM;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_BANK_ACCOUNT;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_INFO_ACCOUNT;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_MAKE_MONEY;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_NEWS;
import static vn.doithe66.doithe66.Utils.Constant.ITEM_TRANSFER_MONEY;
import static vn.doithe66.doithe66.Utils.Constant.KEY_TOKEN;
import static vn.doithe66.doithe66.Utils.Constant.LIST_CARD;
import static vn.doithe66.doithe66.Utils.Constant.USER_INFO;
import static vn.doithe66.doithe66.fragment.DiscountFragment.DISCOUNT_CARD;

/**
 * Created by Windows 10 Now on 1/9/2018.
 */

public class HomeFragment extends BaseFragment implements HomeFrmIterator, ItemMenuAdapter.OnHandleClickItemMenu {
    @BindView(R.id.frm_home_txt_card_to_money)
    TextView frmHomeTxtCardToMoney;
    @BindView(R.id.frm_home_txt_money)
    TextView frmHomeTxtMoney;
    @BindView(R.id.frm_home_txt_pay_code_card)
    TextView frmHomeTxtPayCodeCard;
    @BindView(R.id.frm_home_txt_pay_phone)
    TextView frmHomeTxtPayPhone;
    @BindView(R.id.action_bar_top)
    LinearLayout actionBarTop;
    @BindView(R.id.frm_home_img_money)
    ImageView frmHomeImgMoney;
    @BindView(R.id.rl_total_money)
    RelativeLayout rlTotalMoney;
    @BindView(R.id.frm_home_rcl_item_menu)
    RecyclerView frmHomeRclItemMenu;

    private RecyclerView.LayoutManager layoutManager;
    private ItemMenuAdapter itemMenuAdapter;
    private HomeFrmPresenter mHomeFrmPresenter;
    private HomeFrmIteratorImpl homeFrmIterator;
    private ArrayList<ItemCard> mCardArrayList;
    private ArrayList<ItemMenu> menuArrayList;
    private String token;
    private UserInfo mUserInfo;

    public static HomeFragment newInstance(String token, ArrayList<ItemCard> itemCards, UserInfo mUserInfo) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TOKEN, token);
        args.putParcelable(USER_INFO, mUserInfo);
        args.putParcelableArrayList(DISCOUNT_CARD, itemCards);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    public void setHomeFrmPresenter(HomeFrmPresenter homeFrmPresenter) {
        mHomeFrmPresenter = homeFrmPresenter;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        UserInfo mUserInfo = ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class));
        AutoLogin autoLogin = new AutoLogin(getActivity());
        if (!autoLogin.bLogin) {
            autoLogin.login(mUserInfo.getPassWord());
        }
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCardArrayList = new ArrayList<>();
            mCardArrayList = getArguments().getParcelableArrayList(DISCOUNT_CARD);
            token = getArguments().getString(KEY_TOKEN);
            mUserInfo = (UserInfo) bundle.getParcelable(USER_INFO);
        }
        frmHomeTxtMoney.setText(mUserInfo.getSoDuKhaDung() + " " + "đ");
        homeFrmIterator = new HomeFrmIteratorImpl(menuArrayList);
        homeFrmIterator.setHomeFrmIterator(this);
    }

    @OnClick({R.id.frm_home_txt_card_to_money, R.id.frm_home_txt_pay_code_card, R.id.frm_home_txt_pay_phone, R.id.frm_home_rcl_item_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frm_home_txt_card_to_money:
                Toast.makeText(getActivity(), "Đổi thẻ 66 tạm ngừng cung cấp dịch vụ đổi thẻ cào thành tiền mặt", Toast.LENGTH_LONG).show();
                break;
            case R.id.frm_home_txt_pay_code_card:
                Intent intent = new Intent(getActivity(), GenaralActivity.class);
                intent.putParcelableArrayListExtra(LIST_CARD, mCardArrayList);
                intent.putExtra(Constant.FROM_ITEM, Constant.ITEM_PAY_CARD);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;
            case R.id.frm_home_txt_pay_phone:
                Intent intent1 = new Intent(getActivity(), GenaralActivity.class);
                intent1.putExtra(FROM_ITEM, Constant.ITEM_PAY_PHONE);
                startActivity(intent1);
                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;
        }
    }

    @Override
    public void genarateItemMenu(ArrayList<ItemMenu> itemMenus) {
        menuArrayList = new ArrayList<>();
        menuArrayList.addAll(itemMenus);
        frmHomeRclItemMenu.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 3);
        frmHomeRclItemMenu.setLayoutManager(layoutManager);
        itemMenuAdapter = new ItemMenuAdapter(getActivity(), menuArrayList);
        frmHomeRclItemMenu.setAdapter(itemMenuAdapter);
        itemMenuAdapter.setOnHandleClickItemMenu(this);
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(getActivity(), GenaralActivity.class);
        switch (++position) {
//            case 0:
//                intent.putExtra(FROM_ITEM, Constant.ITEM_EXCHANGE_CARD);
//                Toast.makeText(getActivity(), "Đổi thẻ 66 tạm ngừng cung cấp dịch vụ đổi thẻ cào thành tiền mặt", Toast.LENGTH_LONG).show();
//                return;
            case 1:
                intent.putParcelableArrayListExtra(LIST_CARD, mCardArrayList);
                intent.putExtra(Constant.FROM_ITEM, Constant.ITEM_PAY_CARD);
                break;
            case 2:
                intent.putExtra(FROM_ITEM, Constant.ITEM_PAY_PHONE);
                break;
            case 3:
                loadFragment(HistoryTransactionFragment.newInstance(token));
                return;
//                intent.putExtra(FROM_ITEM, Constant.ITEM_HISTORY);
//                break;
            case 4:
                intent.putExtra(FROM_ITEM, ITEM_BANK_ACCOUNT);
                break;
            case 5:
                intent.putExtra(FROM_ITEM, ITEM_MAKE_MONEY);
                break;
            case 6:
                intent.putExtra(FROM_ITEM, ITEM_TRANSFER_MONEY);
                break;
            case 7:
                intent.putExtra(FROM_ITEM, ITEM_INFO_ACCOUNT);
                break;
            case 8:
                intent.putExtra(FROM_ITEM, ITEM_NEWS);
        }
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fr_main, fragment, Fragment.class.getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
