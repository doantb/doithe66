package vn.doithe66.doithe66.Utils;

import java.util.ArrayList;

import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.model.BankType;

public class ConfigListBank {
    public static boolean isInit = false;

    public static void initDataBank(
            ArrayList<BankType> listBankType) {
        listBankType = new ArrayList<>();
        listBankType.add(new BankType("Agribank - NH nông nghiệp và phát triển nông thôn", "Agribank", R.drawable.logo_agribank));
        listBankType.add(new BankType("BIDV - NH đầu tư và phát triển", "BIDV", R.drawable.logo_bidv));
        listBankType.add(new BankType("Vietinbank - NHTMCP công thương", "Vietinbank", R.drawable.logo_vietinbank));
        listBankType.add(new BankType("Vietcombank - NHTMCP Ngoại thương", "Vietcombank", R.drawable.logo_vietcombank));
        listBankType.add(new BankType("Techcombank - NHTMCP Kỹ thương", "Techcombank", R.drawable.logo_techcom));
        listBankType.add(new BankType("SCB - NHTMCP Sài Gòn", "SCB", R.drawable.logo_bidv));
        listBankType.add(new BankType("Navibank - NHTMCP Nam Việt", "Navibank", R.drawable.logo_techcom));
        listBankType.add(new BankType("TienPhongBank - NHTMCP Tiền Phong", "TienPhongBank", R.drawable.logo_vnmart));
        listBankType.add(new BankType("VietABank - NHTMCP Việt Á", "VietABank", R.drawable.logo_dongabank));
        listBankType.add(new BankType("VIBBank - NHTMCP quốc tế", "VIBBank", R.drawable.logo_maritime_bank));
        listBankType.add(new BankType("EIBBank - NHTMCP xuất nhập khẩu", "EIBBank", R.drawable.logo_namabank));
        listBankType.add(new BankType("HDBank - NHTMCP phát triển TP.HCM", "HDBank", R.drawable.logo_hdbank));
        listBankType.add(new BankType("IVBBank - NHTNHH Indovina", "IVBBank", R.drawable.logo_mb_bank));
        listBankType.add(new BankType("SHBBank - NHTMCP Sài Gòn Hà Nội", "SHBBank", R.drawable.logo_acb_bank));
        listBankType.add(new BankType("DongABank - NHTMCP Đông Á", "DongABank", R.drawable.logo_dongabank));
        listBankType.add(new BankType("ACBBank - NHTMCP Á Châu ", "ACBBank", R.drawable.logo_acb_bank));
        listBankType.add(new BankType("SeABank - NHTMCP Đông Nam Á ", "SeABank", R.drawable.logo_exim_bank));
        listBankType.add(new BankType("Sacombank - NHTMCP Sài Gòn thương tín", "Sacombank", R.drawable.logo_sacombank));
        listBankType.add(new BankType("Saigonbank - NHTMCP Sài Gòn công thương", "Saigonbank", R.drawable.logo_agribank));
        listBankType.add(new BankType("ABBank - NHTMCP An Bình ", "ABBank", R.drawable.logo_visa));
        listBankType.add(new BankType("MHBbank - NHPT nhà Đồng bằng sông Cửu Long", "MHBbank", R.drawable.logo_visa));
        listBankType.add(new BankType("Westernbank - NHTMCP Phương Tây", "Westernbank", R.drawable.logo_visa));
        listBankType.add(new BankType("Oceanbank - NHTMCP Đại Dương ", "Oceanbank", R.drawable.logo_oceanbank));
        listBankType.add(new BankType("Westernbank - NHTMCP Phương Tây", "Westernbank", R.drawable.logo_visa));
        listBankType.add(new BankType("PGBank - NH xăng dầu Petrolimex", "PGBank", R.drawable.logo_visa));
        listBankType.add(new BankType("VRBbank - NH liên doanh Việt Nga", "VRBbank", R.drawable.logo_visa));
        listBankType.add(new BankType("TRUSTBank - NHTMCP Đại Tín", "TRUSTBank", R.drawable.logo_visa));
        listBankType.add(new BankType("NamABank - NHTMCP Nam Á ", "NamABank", R.drawable.logo_visa));
        listBankType.add(new BankType("BacABank - NHTMCP Bắc Á", "BacABank", R.drawable.logo_visa));
        listBankType.add(new BankType("GPBank - NHTMCP Dầu khí toàn cầu", "GPBank", R.drawable.logo_visa));
        listBankType.add(new BankType("CCF - Quĩ tín dụng nhân dân trung ương", "CCF", R.drawable.logo_visa));
        listBankType.add(new BankType("DaiABank - NH TMCP Đại Á", "DaiABank", R.drawable.logo_visa));
        listBankType.add(new BankType("MBbank - NHTMCP quân đội", "MBbank", R.drawable.logo_mb_bank));
        listBankType.add(new BankType("Eximbank - NH xuất nhập khẩu", "Eximbank", R.drawable.logo_exim_bank));
        listBankType.add(new BankType("Maritime - Bank NHTMCP hàng hải", "Maritime Bank", R.drawable.logo_maritime_bank));
        listBankType.add(new BankType("VPBank - NH Việt Nam thịnh vượng", "VPBank", R.drawable.logo_vp_bank));
        listBankType.add(new BankType("ShinhanBank - NH Shinhan", "ShinhanBank", R.drawable.logo_visa));
        listBankType.add(new BankType("OCB - NH Phương Đông", "OCB", R.drawable.logo_ocb_bank));
        saveListBankType(listBankType);
    }

    private static void saveListBankType(ArrayList<BankType> bankTypes) {
        SharedPrefs.getInstance().put(Constant.LIST_BANK_TYPE, ConfigJson.setListBankType(bankTypes));
        isInit = true;
    }

    public static ArrayList<BankType> getListBank() {
        return (ArrayList<BankType>) ConfigJson.getListBankType(SharedPrefs.getInstance().get(Constant.LIST_BANK_TYPE, String.class));
    }
}
