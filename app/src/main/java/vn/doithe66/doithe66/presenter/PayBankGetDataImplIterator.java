package vn.doithe66.doithe66.presenter;

import java.util.ArrayList;

import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.BankType;
import vn.doithe66.doithe66.view.PayBankGetDataForListIterator;

import static vn.doithe66.doithe66.Utils.Constant.PAY_CARD_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.PAY_FAST_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.TAKE_MONEY;

/**
 * Created by Windows 10 Now on 1/19/2018.
 */

public class PayBankGetDataImplIterator implements PayBankGetDataForListIterator {

    @Override
    public void initDataForPayBank(int positionFragment, ArrayList<BankType> bankTypes) {
        switch (positionFragment) {
            case PAY_CARD_FRAGMENT:
                bankTypes.addAll(initFaceDataLoadMoneyFast(bankTypes));
                break;
            case PAY_FAST_FRAGMENT:
                bankTypes.addAll(initFaceDataLoadMoneyFast(bankTypes));
                break;
            case TAKE_MONEY:
                bankTypes.addAll(initFaceDataViettelSlowFragment(bankTypes));
                break;
        }
    }

    public static ArrayList<BankType> initFaceDataViettelSlowFragment(
            ArrayList<BankType> lisBankViettelSlowFragment) {
        lisBankViettelSlowFragment = new ArrayList<>();
        lisBankViettelSlowFragment.add(new BankType("Vietcombank", R.drawable.logo_vietcombank));
        lisBankViettelSlowFragment.add(new BankType("SCB", R.drawable.logo_sacombank));
        lisBankViettelSlowFragment.add(new BankType("Oceanbank", R.drawable.logo_oceanbank));
        lisBankViettelSlowFragment.add(new BankType("HDBank", R.drawable.logo_hdbank));
        lisBankViettelSlowFragment.add(new BankType("Vietinbank", R.drawable.logo_vietinbank));
        lisBankViettelSlowFragment.add(new BankType("BIDV", R.drawable.logo_bidv));
        lisBankViettelSlowFragment.add(new BankType("Techcombank", R.drawable.logo_techcom));
        lisBankViettelSlowFragment.add(new BankType("Vn Mart", R.drawable.logo_vnmart));
        lisBankViettelSlowFragment.add(new BankType("DongABank", R.drawable.logo_dongabank));
        lisBankViettelSlowFragment.add(
                new BankType("Maritime Bank", R.drawable.logo_maritime_bank));
        lisBankViettelSlowFragment.add(new BankType("NamABank", R.drawable.logo_namabank));
        lisBankViettelSlowFragment.add(new BankType("TienPhongBank", R.drawable.logo_tpbank));
        return lisBankViettelSlowFragment;
    }

    public static ArrayList<BankType> initFaceDataLoadMoneyFast(
            ArrayList<BankType> lisBankLoadMoneyFast) {
        lisBankLoadMoneyFast = new ArrayList<>();
        lisBankLoadMoneyFast.add(new BankType("Vietcombank", R.drawable.logo_vietcombank));
        lisBankLoadMoneyFast.add(new BankType("SCB", R.drawable.logo_sacombank));
        lisBankLoadMoneyFast.add(new BankType("Oceanbank", R.drawable.logo_oceanbank));
        lisBankLoadMoneyFast.add(new BankType("HDBank", R.drawable.logo_hdbank));
        lisBankLoadMoneyFast.add(new BankType("Vietinbank", R.drawable.logo_vietinbank));
        lisBankLoadMoneyFast.add(new BankType("BIDV", R.drawable.logo_bidv));
        lisBankLoadMoneyFast.add(new BankType("Techcombank", R.drawable.logo_techcom));
        lisBankLoadMoneyFast.add(new BankType("Vn Mart", R.drawable.logo_vnmart));
        lisBankLoadMoneyFast.add(new BankType("DongABank", R.drawable.logo_dongabank));
        lisBankLoadMoneyFast.add(new BankType("Maritime Bank", R.drawable.logo_maritime_bank));
        lisBankLoadMoneyFast.add(new BankType("NamABank", R.drawable.logo_namabank));
        lisBankLoadMoneyFast.add(new BankType("TienPhongBank", R.drawable.logo_tpbank));
        lisBankLoadMoneyFast.add(new BankType("MSBBank", R.drawable.logo_mb_bank));
        lisBankLoadMoneyFast.add(new BankType("ACBBank", R.drawable.logo_acb_bank));
        lisBankLoadMoneyFast.add(new BankType("OCB", R.drawable.logo_ocb_bank));
        lisBankLoadMoneyFast.add(new BankType("NCB", R.drawable.logo_ncb_bank));
        lisBankLoadMoneyFast.add(new BankType("Eximbank", R.drawable.logo_exim_bank));
        lisBankLoadMoneyFast.add(new BankType("VPBank", R.drawable.logo_vp_bank));
        lisBankLoadMoneyFast.add(new BankType("Agribank", R.drawable.logo_agribank));
        lisBankLoadMoneyFast.add(new BankType("Visa", R.drawable.logo_visa));
        return lisBankLoadMoneyFast;
    }
}
