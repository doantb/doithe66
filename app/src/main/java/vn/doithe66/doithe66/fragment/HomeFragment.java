package vn.doithe66.doithe66.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.adapter.ItemMenuAdapter;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.model.ItemMenu;
import vn.doithe66.doithe66.presenter.HomeFrmIterator;
import vn.doithe66.doithe66.presenter.HomeFrmIteratorImpl;
import vn.doithe66.doithe66.presenter.HomeFrmPresenter;

import static vn.doithe66.doithe66.Utils.Constant.KEY_TOKEN;
import static vn.doithe66.doithe66.fragment.DiscountFragment.DISCOUNT_CARD;

/**
 * Created by Windows 10 Now on 1/9/2018.
 */

public class HomeFragment extends BaseFragment implements HomeFrmIterator {
    @BindView(R.id.frm_home_txt_card_to_money)
    TextView frmHomeTxtCardToMoney;
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

    public static HomeFragment newInstance(String token, ArrayList<ItemCard> itemCards) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TOKEN, token);
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
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCardArrayList = new ArrayList<>();
            mCardArrayList = getArguments().getParcelableArrayList(DISCOUNT_CARD);
            token = getArguments().getString(KEY_TOKEN);
        }
        homeFrmIterator = new HomeFrmIteratorImpl(menuArrayList);
        homeFrmIterator.setHomeFrmIterator(this);
    }

    @OnClick({R.id.frm_home_txt_card_to_money, R.id.frm_home_txt_pay_code_card, R.id.frm_home_txt_pay_phone, R.id.frm_home_rcl_item_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frm_home_txt_card_to_money:
                break;
            case R.id.frm_home_txt_pay_code_card:
                break;
            case R.id.frm_home_txt_pay_phone:
                break;
        }
    }

    @Override
    public void genarateItemMenu(ArrayList<ItemMenu> itemMenus) {
        menuArrayList.addAll(itemMenus);
        frmHomeRclItemMenu.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 3);
        frmHomeRclItemMenu.setLayoutManager(layoutManager);
        itemMenuAdapter = new ItemMenuAdapter(getActivity(), menuArrayList);
        frmHomeRclItemMenu.setAdapter(itemMenuAdapter);
    }
}
