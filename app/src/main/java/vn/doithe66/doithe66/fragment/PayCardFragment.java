package vn.doithe66.doithe66.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.activity.PayBankActivity;
import vn.doithe66.doithe66.adapter.CardTypeAdapter;
import vn.doithe66.doithe66.adapter.PriceCardAdapter;
import vn.doithe66.doithe66.model.Amount;
import vn.doithe66.doithe66.model.InfoUserEdit;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.presenter.PayCardFrmPresenter;
import vn.doithe66.doithe66.view.PayCardView;
import vn.doithe66.doithe66.view.ProgessView;

import static vn.doithe66.doithe66.Utils.Constant.EMAIL;
import static vn.doithe66.doithe66.Utils.Constant.FROM_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.INFO_USER_EDIT;
import static vn.doithe66.doithe66.Utils.Constant.KEY_TOKEN;
import static vn.doithe66.doithe66.Utils.Constant.PAY_CARD_FRAGMENT;
import static vn.doithe66.doithe66.fragment.DiscountFragment.DISCOUNT_CARD;

/**
 * Created by Windows 10 Now on 1/10/2018.
 */

public class PayCardFragment extends BaseFragment
        implements ProgessView, PayCardView, CardTypeAdapter.onHandleClick, PriceCardAdapter.onHandleClickPrice {

    @BindView(R.id.frm_pay_card_rcl_item_card)
    RecyclerView frmPayCardRclItemCard;
    @BindView(R.id.frm_pay_card_rcl_price)
    RecyclerView frmPayCardRclPrice;
    @BindView(R.id.frm_pay_card_sub)
    ImageView frmPayCardSub;
    @BindView(R.id.frm_pay_card_txt_count)
    TextView frmPayCardTxtCount;
    @BindView(R.id.frm_pay_card_add)
    ImageView frmPayCardAdd;
    @BindView(R.id.frm_pay_card_txt_price)
    TextView frmPayCardTxtPrice;
    @BindView(R.id.frm_pay_card_txt_discount)
    TextView frmPayCardTxtDiscount;
    @BindView(R.id.frm_pay_card_txt_title_discount)
    TextView frmPayCardTxtTitleDiscount;
    @BindView(R.id.frm_pay_card_btn_continue)
    Button frmPayCardBtnContinue;
    @BindView(R.id.frm_pay_card_rl_total)
    RelativeLayout mFrmPayCardRlTotal;
    @BindView(R.id.bt_sheet_edt_pass_level2)
    EditText mBtSheetEdtPassLevel2;
    @BindView(R.id.bt_sheet_edt_email)
    EditText mBtSheetEdtEmail;
    @BindView(R.id.bt_sheet_buy_now)
    Button btSheetBuyNow;
    @BindView(R.id.home_bottom_sheet)
    RelativeLayout mHomeBottomSheet;
    @BindView(R.id.my_progess_bar)
    RelativeLayout mMyProgessBar;

    private CardTypeAdapter cardTypeAdapter;
    private PriceCardAdapter priceCardAdapter;
    private RecyclerView.LayoutManager layoutManagerCard;
    private RecyclerView.LayoutManager layoutManagerPrice;
    private BottomSheetBehavior mSheetBehavior;
    private ArrayList<ItemCard> mItemCards;
    private ArrayList<String> ListTypeCard;
    private ArrayList<String> ListPrice;
    private PayCardFrmPresenter mPayCardFrmPresenter;
    private ArrayList<ItemCard> mCardArrayList;
    private ItemCard itemCard;
    ArrayList<Amount> amounts;
    private String token;
    private static int countCard = 1;
    private int posPrice = 0;

    public static PayCardFragment newInstance(String token, ArrayList<ItemCard> cardArrayList) {
        PayCardFragment payCardFragment = new PayCardFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TOKEN, token);
        args.putParcelableArrayList(DISCOUNT_CARD, cardArrayList);
        payCardFragment.setArguments(args);
        return payCardFragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_pay_card;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCardArrayList = new ArrayList<>();
            mCardArrayList = getArguments().getParcelableArrayList(DISCOUNT_CARD);
            token = getArguments().getString(KEY_TOKEN);
        }
        initRecyclerViewCardType();
        initRecyclerViewPrice();
        initBottomSheet();
        ListTypeCard = new ArrayList<>();
        ListPrice = new ArrayList<>();
//        mPayCardFrmPresenter = new PayCardFrmPresenterImpl(this, this);
//        mPayCardFrmPresenter.getDataPayCard(mCardArrayList, ListTypeCard, ListPrice, -1);
//        mTxtDiscount.setText("Chiết khấu (" + mCardArrayList.get(0).getDisCount() + ")");
//        final String[] valuesTypeCard = ListTypeCard.toArray(new String[ListTypeCard.size()]);
//        final String[] valuesPrice = ListPrice.toArray(new String[ListPrice.size()]);
    }

    private void initRecyclerViewPrice() {
        amounts = new ArrayList<>();
        amounts.addAll(mCardArrayList.get(0).getmAmounts());
        mCardArrayList.get(0).getmAmounts().get(0).setWatch(true);
        frmPayCardRclPrice.setHasFixedSize(true);
        layoutManagerPrice = new GridLayoutManager(getActivity(), 4);
        frmPayCardRclPrice.setLayoutManager(layoutManagerPrice);
        priceCardAdapter = new PriceCardAdapter(getActivity(), amounts);
        frmPayCardRclPrice.setAdapter(priceCardAdapter);
        priceCardAdapter.setmOnHandleClick(this);
        frmPayCardTxtDiscount.setText(String.valueOf(new DecimalFormat("##.##").format((Integer.parseInt(amounts.get(0).getAmount()) * countCard * itemCard.getDisCountDouble() / 100))) + " đ");
        frmPayCardTxtPrice.setText(String.valueOf(Integer.parseInt(amounts.get(0).getAmount()) * countCard) + " đ");
        frmPayCardTxtTitleDiscount.setText("Chiết khấu " + itemCard.getDisCount() + " khi mua");
    }

    private void initRecyclerViewCardType() {
        itemCard = mCardArrayList.get(0);
        frmPayCardTxtCount.setText(String.valueOf(countCard));
        mCardArrayList.get(0).setWatch(true);
        frmPayCardRclItemCard.setHasFixedSize(true);
        layoutManagerCard = new GridLayoutManager(getActivity(), 4);
        frmPayCardRclItemCard.setLayoutManager(layoutManagerCard);
        cardTypeAdapter = new CardTypeAdapter(getActivity(), mCardArrayList);
        frmPayCardRclItemCard.setAdapter(cardTypeAdapter);
        cardTypeAdapter.setOnHandleClick(this);
    }

    private void initBottomSheet() {
        mSheetBehavior = BottomSheetBehavior.from(mHomeBottomSheet);
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        mFrmPayCardRlTotal.setVisibility(View.GONE);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        //close bottom sheet
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        //open sheet
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void showProgess() {
        mMyProgessBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgess() {
        mMyProgessBar.setVisibility(View.GONE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick(R.id.frm_pay_card_btn_continue)
    public void onViewClicked() {
        if (mSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            mSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            mFrmPayCardRlTotal.setVisibility(View.VISIBLE);
            mBtSheetEdtEmail.setText(
                    SharedPrefs.getInstance().get(EMAIL, String.class));
        } else {
            mSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            mFrmPayCardRlTotal.setVisibility(View.GONE);
            mBtSheetEdtEmail.setText(
                    SharedPrefs.getInstance().get(EMAIL, String.class));
        }
    }

    @OnClick(R.id.frm_pay_card_rl_total)
    public void onTotalClicked() {
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @OnClick(R.id.bt_sheet_buy_now)
    public void onBtSheetBuyNowClicked() {
        Intent intent = new Intent(getActivity(), PayBankActivity.class);
        InfoUserEdit infoUserEdit = new InfoUserEdit();
        if (mBtSheetEdtPassLevel2.getText() != null && !mBtSheetEdtPassLevel2.getText().toString().equals("")) {
//            int count = Integer.parseInt(mBtSheetEdtPassLevel2.getText().toString());
            infoUserEdit.setPasslv2(mBtSheetEdtPassLevel2.getText().toString());
            infoUserEdit.setCountBuy(countCard);
            infoUserEdit.setProviderId(itemCard.getProviderId());
            infoUserEdit.setProviderCode(
                    itemCard.getProviderCode());
            infoUserEdit.setEmail(SharedPrefs.getInstance().get(EMAIL, String.class));
            infoUserEdit.setTypeCard(itemCard.getNameHomeNetWork());
            infoUserEdit.setPrice(amounts.get(posPrice).getAmount());
            intent.putExtra(FROM_FRAGMENT, PAY_CARD_FRAGMENT);
            intent.putExtra(INFO_USER_EDIT, infoUserEdit);
            startActivity(intent);
        } else {
            mBtSheetEdtPassLevel2.setError("Bạn chưa nhập mật khẩu cấp 2");
            return;
        }
    }

    @Override
    public void onLoadDataSuccess() {
        //        final String[] valuesTypeCard = ListTypeCard.toArray(new String[0]);
    }

    @OnClick({R.id.frm_pay_card_sub, R.id.frm_pay_card_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frm_pay_card_sub:
                if (countCard > 0 && countCard != 1) {
                    countCard--;
                    frmPayCardTxtCount.setText(String.valueOf(countCard));
                    frmPayCardTxtDiscount.setText(String.valueOf(new DecimalFormat("##.##").format((Integer.parseInt(amounts.get(posPrice).getAmount()) * countCard * itemCard.getDisCountDouble() / 100))) + " đ");
                    frmPayCardTxtPrice.setText(String.valueOf(Integer.parseInt(amounts.get(posPrice).getAmount()) * countCard) + " đ");
                } else {
                    Toast.makeText(getActivity(), "Bạn phải chọn số lượng lớn hơn 0", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.frm_pay_card_add:
                countCard++;
                frmPayCardTxtCount.setText(String.valueOf(countCard));
                frmPayCardTxtDiscount.setText(String.valueOf(new DecimalFormat("##.##").format((Integer.parseInt(amounts.get(posPrice).getAmount()) * countCard * itemCard.getDisCountDouble() / 100))) + " đ");
                frmPayCardTxtPrice.setText(String.valueOf(Integer.parseInt(amounts.get(posPrice).getAmount()) * countCard) + " đ");
                break;
        }
    }

    @Override
    public void onClickCardType(int position) {
        itemCard = mCardArrayList.get(position);
        Toast.makeText(getActivity(), "Bạn đã chọn thẻ : " + mCardArrayList.get(position).getNameHomeNetWork(), Toast.LENGTH_SHORT).show();
        frmPayCardTxtTitleDiscount.setText("Chiết khấu " + itemCard.getDisCount() + " khi mua");
        frmPayCardTxtDiscount.setText(String.valueOf(new DecimalFormat("##.##").format((Integer.parseInt(amounts.get(posPrice).getAmount()) * countCard * itemCard.getDisCountDouble() / 100))) + " đ");
        frmPayCardTxtPrice.setText(String.valueOf(Integer.parseInt(amounts.get(posPrice).getAmount()) * countCard) + " đ");
        amounts = itemCard.getmAmounts();
        amounts.get(0).setWatch(true);
        priceCardAdapter.addAll(amounts);
        for (int i = 0; i < mCardArrayList.size(); i++) {
            mCardArrayList.get(i).setWatch(false);
        }
        mCardArrayList.get(position).setWatch(true);
        cardTypeAdapter.notifyDataSetChanged();
        priceCardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickPrice(int position) {
        posPrice = position;
        for (int i = 0; i < amounts.size(); i++) {
            amounts.get(i).setWatch(false);
        }
        frmPayCardTxtDiscount.setText(String.valueOf(new DecimalFormat("##.##").format((Integer.parseInt(amounts.get(posPrice).getAmount()) * countCard * itemCard.getDisCountDouble() / 100))) + " đ");
        frmPayCardTxtPrice.setText(String.valueOf(Integer.parseInt(amounts.get(position).getAmount()) * countCard) + " đ");
        amounts.get(position).setWatch(true);
        priceCardAdapter.notifyDataSetChanged();
    }
}
