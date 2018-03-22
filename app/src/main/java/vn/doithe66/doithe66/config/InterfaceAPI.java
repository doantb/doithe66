package vn.doithe66.doithe66.config;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vn.doithe66.doithe66.model.DiscountNumber;
import vn.doithe66.doithe66.model.GetDiscountCard;
import vn.doithe66.doithe66.model.RegisterMDResult;
import vn.doithe66.doithe66.model.ResultBanThe;
import vn.doithe66.doithe66.model.ResultHistory;
import vn.doithe66.doithe66.model.ResultJsonApi;
import vn.doithe66.doithe66.model.UserInfo;

/**
 * Created by Windows 10 Now on 11/16/2017.
 */

public interface InterfaceAPI {
    //api login
    @POST("v2/PayCard/LoginV2")
    Call<String> login(@Query("userName") String username, @Query("password") String password);

    //api register
    @POST("v2/PayCard/Register")
    Call<RegisterMDResult> register(@Query("fName") String username, @Query("phone") String phone,
            @Query("email") String email, @Query("passwd") String passwd);

    //api user info
    @POST("v2/PayCard/UserInfo")
    Call<UserInfo> getUserInfo();

    //api login with gmail
    @POST("v2/PayCard/DangNhapGoogle")
    Call<RegisterMDResult> loginWithGoogle(@Query("Token") String Token,
            @Query("FullName") String FullName, @Query("PassWord") String PassWord);

    //api create new pass
    @POST("v2/PayCard/DoiMatKhauCap1")
    Call<RegisterMDResult> createNewPass(@Query("matKhauCu") String oldPass,
            @Query("matKhauMoi") String newPass);

    //api discount agency
    @POST("v2/PayCard/ListProvider")
    Call<ArrayList<DiscountNumber>> getPriceCard();

    //api get discount_card
    @POST("v2/PayCard/ListProvider")
    Call<ArrayList<GetDiscountCard>> getDiscountCard();

    //api get discount_card
    @POST("v2/PayCard/ListProvider")
    Call<ArrayList<GetDiscountCard>> getDiscountCardIsMember(@Header("token") String token);

    //api pay card Viettel Slow
    @POST("v2/PayCard/NapTienCham")
    Call<RegisterMDResult> payCardViettelSlow(@Query("bankCode") String bankCode,
            @Query("sodienthoai") String sodienthoai, @Query("amount") int amount,
            @Query("email") String email, @Query("telcotype") String telcotype);

    //api pay card fast
    @POST("v2/PayCard/NapTienDTBangBank")
    Call<RegisterMDResult> payCardFast(@Query("bankCode") String bankCode,
            @Query("sodienthoai") String sodienthoai, @Query("amount") int amount,
            @Query("email") String email, @Query("telcotype") String telcotype);

    //api pay card by bank
    @POST("v2/PayCard/MuaTheBangBank")
    Call<RegisterMDResult> payCardByBank(@Query("bankCode") String bankCode,
            @Query("emailnhan") String email, @Query("providerId") int providerId,
            @Query("amount") int amount, @Query("quantity") String quantity);

    //api pay card by bank by banthe24
    @POST("v2/PayCards/TelcoPay/GetCards")
    Call<ResultBanThe> payCardByBankBanThe(@Query("msg") String msg);

    //api pay card fast by banthe24
    @POST("v2/PayCards/TelcoPay/TopupMobile")
    Call<ResultBanThe> payCardFastBanThe(@Query("msg") String msg);

    //api pay card Viettel Slow by banthe24
    @POST("v2/PayCards/TelcoPay/TopupMobileVT")
    Call<ResultBanThe> payCardViettelSlowBanThe(@Query("msg") String msg);

    //api lich su nap tien tai khoan
    @POST("v2/PayCard/LichSuNapTienTaiKhoan")
    Call<ResultHistory> historyPayCardByBank(@Query("fromDate") String fromDate,
            @Query("toDate") String toDate, @Query("trangThai") int trangThai,
            @Query("page") int page);

    //api lich su nap tien dien thoai:
    //    @POST("v2/PayCard/LichSuNapTienDT")
    //    Call<ResultHistory> getHistoryLoadMoneyFone(@Query("fromDate") String fromDate,
    //            @Query("toDate") String toDate, @Query("page") int page);

    //api lich su nap tien dien thoai nhanh:
    @POST("v2/PayCard/LichSuNapTienDTNhanh")
    Call<ResultHistory> getHistoryLoadMoneyFoneFast(@Query("fromDate") String fromDate,
            @Query("toDate") String toDate, @Query("page") int page);

    //api lich su nap tien dien thoai cham:
    @POST("v2/PayCard/LichSuNapTienCham")
    Call<ResultHistory> getHistoryLoadMoneyFoneSlow(
            @Query("searchString") String sFoneLoadMoneySlow, @Query("fromDate") String fromDate,
            @Query("toDate") String toDate, @Query("page") int page);

    // api lich su mua ma the:
    @POST("v2/PayCard/LichSuMuaMaThe")
    Call<ResultHistory> getHistoryBuyCard(@Query("fromDate") String fromDate,
            @Query("toDate") String toDate, @Query("menhGia") String menhGia,
            @Query("page") int page);

    //api quen mat khau:
    @POST("v2/PayCard/QuenMatKhau")
    Call<ResultJsonApi> getForgetPassWordByEmail(@Query("email") String email);

    //api cap nhat thong tin tai khoan:
    @POST("v2/PayCard/CapNhatThongTinTK")
    Call<ResultJsonApi> updateAccountInfor(@Query("fName") String fName,
            @Query("phone") String phone, @Query("bankNumber") String bankNumber,
            @Query("bankAccount") String bankAccount, @Query("bankCode") String bankCode,
            @Query("bankBranch") String bankBranch);

    //api nap tien dien thoai
    @POST("v2/PayCards/TelcoPay/NapSoDienThoai")
    Call<ResultBanThe> payMoneyForMobile(@Query("msg") String msg);

    //api update nap tien dien thoai
    @POST("v2/PayCards/TelcoPay/UpdateNapSoDienThoai")
    Call<ResultBanThe> UpdatePayMoneyForMobile(@Query("msg") String msg);
}
