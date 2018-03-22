package vn.doithe66.doithe66.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import vn.doithe66.doithe66.fragment.PayCardFragment;
import vn.doithe66.doithe66.fragment.PayFastFragment;
import vn.doithe66.doithe66.fragment.PaySlowFragment;
import vn.doithe66.doithe66.model.ItemCard;

/**
 * Created by Windows 10 Now on 1/10/2018.
 */

public class VPHomeAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mFragmentArrayList = new ArrayList<>();
    private String token;
    private ArrayList<ItemCard> mItemCards = new ArrayList<>();

    public void addFragment(Fragment fragment) {
        mFragmentArrayList.add(fragment);
    }

    public VPHomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PayCardFragment.newInstance(token, mItemCards);
            case 1:
                return PayFastFragment.newInstance("abc");
            case 2:
                return PaySlowFragment.newInstance("abc");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mFragmentArrayList.size();
    }

    public void addAllData(String token, ArrayList<ItemCard> itemCards) {
        this.token = token;
        this.mItemCards.addAll(itemCards);
        notifyDataSetChanged();
    }
}
