package vn.doithe66.doithe66.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.shawnlin.numberpicker.NumberPicker;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import java.text.ParseException;
import java.util.ArrayList;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.activity.PayBankActivity;
import vn.doithe66.doithe66.adapter.PickTypeCardAdapter;
import vn.doithe66.doithe66.model.InfoUserEdit;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.presenter.PayCardFrmPresenter;
import vn.doithe66.doithe66.presenter.PayCardFrmPresenterImpl;
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
        implements DiscreteScrollView.OnItemChangedListener, ProgessView,
        NumberPicker.OnValueChangeListener, PayCardView {
    @BindView(R.id.item_picker)
    DiscreteScrollView mItemPicker;
    @BindView(R.id.item_name)
    TextView mItemName;
    @BindView(R.id.item_price)
    TextView mItemPrice;
    @BindView(R.id.scroll_type_card)
    NumberPicker mScrollTypeCard;
    @BindView(R.id.scroll_price)
    NumberPicker mScrollPrice;
    @BindView(R.id.home_bottom_sheet)
    RelativeLayout mHomeBottomSheet;
    @BindView(R.id.frm_pay_card_txt_buy_now)
    LinearLayout mFrmPayCardTxtBuyNow;
    @BindView(R.id.frm_pay_card_rl_total)
    RelativeLayout mFrmPayCardRlTotal;
    @BindView(R.id.bt_sheet_buy_now)
    Button mBtSheetBuyNow;
    Unbinder unbinder;
    @BindView(R.id.my_progess_bar)
    RelativeLayout mMyProgessBar;
    @BindView(R.id.txt_discount)
    TextView mTxtDiscount;
    @BindView(R.id.bt_sheet_edt_count)
    EditText mBtSheetEdtCount;
    @BindView(R.id.bt_sheet_edt_email)
    EditText mBtSheetEdtEmail;
    private BottomSheetBehavior mSheetBehavior;
    private InfiniteScrollAdapter infiniteAdapter;
    private ArrayList<ItemCard> mItemCards;
    private ArrayList<String> ListTypeCard;
    private ArrayList<String> ListPrice;
    private PayCardFrmPresenter mPayCardFrmPresenter;
    private ArrayList<ItemCard> mCardArrayList;
    private String token;

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
        initBottomSheet();
        ListTypeCard = new ArrayList<>();
        ListPrice = new ArrayList<>();
        mPayCardFrmPresenter = new PayCardFrmPresenterImpl(this, this);
        mPayCardFrmPresenter.getDataPayCard(mCardArrayList, ListTypeCard, ListPrice, -1);
        mTxtDiscount.setText("Chiết khấu (" + mCardArrayList.get(0).getDisCount() + ")");
        final String[] valuesTypeCard = ListTypeCard.toArray(new String[ListTypeCard.size()]);
        final String[] valuesPrice = ListPrice.toArray(new String[ListPrice.size()]);
        mScrollTypeCard.setDisplayedValues(null);
        setScroll(valuesTypeCard, mScrollTypeCard);
        setScroll(valuesPrice, mScrollPrice);
        mItemPicker.setOrientation(Orientation.HORIZONTAL);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new PickTypeCardAdapter(mCardArrayList));
        mItemPicker.setAdapter(infiniteAdapter);
        mItemPicker.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.8f).build());
        //        onChangeTypeCard(sListTypeCard.get(mScrollTypeCard.getValue()), mScrollTypeCard.getValue());
        onChangePrice(ListPrice.get(mScrollPrice.getValue()));
        //        onItemChanged(mItemCards.get(0));
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

    private void setScroll(String[] values, NumberPicker scrollTypeCard) {
        //Populate NumberPicker values from String array values
        //Set the minimum value of NumberPicker
        scrollTypeCard.setMinValue(0); //from array first value
        //Specify the maximum value/number of NumberPicker
        scrollTypeCard.setMaxValue(values.length - 1); //to array last value
        scrollTypeCard.setSelectedTextColorResource(R.color.color_white);

        //Specify the NumberPicker data source as array elements
        scrollTypeCard.setDisplayedValues(values);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        scrollTypeCard.setWrapSelectorWheel(false);
        // set selected text size
        scrollTypeCard.setSelectedTextSize(R.dimen.selected_text_size);
        // set divider color
        scrollTypeCard.setDividerColorResource(R.color.color_tranparent);
        // set text color
        scrollTypeCard.setTextColorResource(R.color.color_white_text_blur);
        //Set a value change listener for NumberPicker
        scrollTypeCard.setOnValueChangedListener(this);
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder,
            int adapterPosition) {
        int positionInDataSet = infiniteAdapter.getRealPosition(adapterPosition);
        onItemChanged(mCardArrayList.get(positionInDataSet));
    }

    private void onItemChanged(ItemCard item) {
        mItemName.setText(item.getNameHomeNetWork());
        mItemPrice.setText(item.getDisCount());
    }

    private void onChangeTypeCard(String s, int newVal) {
        ListPrice.clear();
        mItemName.setText(s);
        mTxtDiscount.setText("Chiết khấu (" + mCardArrayList.get(newVal).getDisCount() + ")");
        mPayCardFrmPresenter.getDataPrice(mCardArrayList, ListPrice, newVal);
        mScrollPrice.setDisplayedValues(null);
        String[] valuesPrice = ListPrice.toArray(new String[ListPrice.size()]);
        setScroll(valuesPrice, mScrollPrice);
        smoothScrollToUserSelectedPosition(mItemPicker, newVal);
        //        mItemPicker.smoothScrollToPosition(newVal);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void onChangePrice(String s) {
        mItemPrice.setText(s);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgess() {
        mMyProgessBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgess() {
        mMyProgessBar.setVisibility(View.GONE);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        switch (picker.getId()) {
            case R.id.scroll_type_card:
                onChangeTypeCard(ListTypeCard.get(newVal), newVal);
                break;
            case R.id.scroll_price:
                onChangePrice(ListPrice.get(newVal));
                break;
        }
    }

    public static void smoothScrollToUserSelectedPosition(final DiscreteScrollView scrollView,
            int pos) {
        final RecyclerView.Adapter adapter = scrollView.getAdapter();
        int itemCount = (adapter instanceof InfiniteScrollAdapter)
                ? ((InfiniteScrollAdapter) adapter).getRealItemCount() : adapter.getItemCount();
        if (adapter instanceof InfiniteScrollAdapter) {
            pos = ((InfiniteScrollAdapter) adapter).getClosestPosition(pos);
        }
        scrollView.smoothScrollToPosition(pos);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick(R.id.frm_pay_card_txt_buy_now)
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
        if (mBtSheetEdtCount.getText() != null && !mBtSheetEdtCount.getText().toString().equals("")) {
            int count = Integer.parseInt(mBtSheetEdtCount.getText().toString());
            infoUserEdit.setCountBuy(count);
            infoUserEdit.setProviderCode(
                    mCardArrayList.get(mScrollTypeCard.getValue()).getProviderCode());
            infoUserEdit.setEmail(SharedPrefs.getInstance().get(EMAIL, String.class));
            infoUserEdit.setTypeCard(ListTypeCard.get(mScrollTypeCard.getValue()));
            infoUserEdit.setPrice(ListPrice.get(mScrollPrice.getValue()));
            intent.putExtra(FROM_FRAGMENT, PAY_CARD_FRAGMENT);
            intent.putExtra(INFO_USER_EDIT, infoUserEdit);
            startActivity(intent);
        } else {
            mBtSheetEdtCount.setError("Bạn chưa nhập số lượng");
            return;
        }
    }

    @Override
    public void onLoadDataSuccess() {
        //        final String[] valuesTypeCard = ListTypeCard.toArray(new String[0]);
    }
}
