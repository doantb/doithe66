package vn.doithe66.doithe66.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;

import java.text.ParseException;
import java.util.ArrayList;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.ItemCard;

/**
 * Created by Windows 10 Now on 1/6/2018.
 */

public class DiscountFragment extends BaseFragment {
    public static final String DISCOUNT_CARD = "discount_card";
    @BindView(R.id.tv_num_vina_muama_the)
    TextView tvNumBuyDiscountVina;
    @BindView(R.id.tv_num_mobi_muama_the)
    TextView tvNumBuyDiscountMobi;
    @BindView(R.id.tv_num_viettel_muama_the)
    TextView tvNumBuyDisCountViettel;
    @BindView(R.id.tv_num_vietnammobi_muama_the)
    TextView tvNumBuyDisCountVietnammobi;
    @BindView(R.id.tv_discount_vcoin)
    TextView tvDisCountVcoin;
    @BindView(R.id.tv_discount_zing)
    TextView tvDisCountZing;
    @BindView(R.id.tv_discount_fptgate)
    TextView tvDisCountfpt;
    //    @BindView(R.id.tv_discount_bit)
    //    TextView tvDisCountBit;
    @BindView(R.id.tv_discount_garena)
    TextView tvDisCountGarena;
    private ArrayList<ItemCard> mItemCards;

    @Override
    protected int layoutId() {
        return R.layout.fragment_discount;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mItemCards = new ArrayList<>();
            mItemCards = getArguments().getParcelableArrayList(DISCOUNT_CARD);
        }
        for (ItemCard itemCard : mItemCards) {
            setDiscountNumber(itemCard.getProviderId(), itemCard);
        }
    }

    public static DiscountFragment newInstance(ArrayList<ItemCard> itemCards) {
        DiscountFragment discountFragment = new DiscountFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(DISCOUNT_CARD, itemCards);
        discountFragment.setArguments(args);
        return discountFragment;
    }

    private void setDiscountNumber(int providerId, ItemCard itemCard) {
        switch (providerId) {
            case 1:
                tvNumBuyDisCountViettel.setText(itemCard.getDisCount());
                break;
            case 2:
                tvNumBuyDiscountMobi.setText(itemCard.getDisCount());
                break;
            case 3:
                tvNumBuyDiscountVina.setText(itemCard.getDisCount());
                break;
            case 5:
                tvDisCountfpt.setText(itemCard.getDisCount());
                break;
            case 7:
                tvNumBuyDisCountVietnammobi.setText(itemCard.getDisCount());
                break;
            case 12:
                tvDisCountZing.setText(itemCard.getDisCount());
                break;
            case 13:
                tvDisCountVcoin.setText(itemCard.getDisCount());
                break;
            case 14:
                tvDisCountGarena.setText(itemCard.getDisCount());
                break;
            case 17:
                //                tvDisCountBit.setText(itemCard.getDisCount());
                break;
        }
    }
}
