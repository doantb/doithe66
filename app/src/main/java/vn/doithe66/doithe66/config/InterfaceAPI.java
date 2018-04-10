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
import vn.doithe66.doithe66.model.ResultCardDoithe;
import vn.doithe66.doithe66.model.ResultHistory;
import vn.doithe66.doithe66.model.ResultJsonApi;
import vn.doithe66.doithe66.model.UserInfo;

/**
 * Created by Windows 10 Now on 11/16/2017.
 */

public interface InterfaceAPI {
    //api login
    @POST("v2/PayCard/DangNhap")
    Call<String> login(@Query("userName") String username, @Query("password") String password);

    //api register
    @POST("v2/PayCard/DangKiTaiKhoan")
    Call<RegisterMDResult> register(@Query("UserName") String username, @Query("Name") String name, @Query("Phone") String phone,
                                    @Query("Email") String email, @Query("Password") String passwd, @Query("Password2") String passwdlv2,
                                    @Query("Address") String Address, @Query("BankNumber") String BankNumber, @Query("BankAccount") String BankAccount,
                                    @Query("BankCode") String BankCode, @Query("BankName") String BankName, @Query("BankBranch") String BankBranch);//api register

    //api update Tk ngân hàng
    @POST("v2/PayCard/CapNhatThongTinTK")
    Call<RegisterMDResult> updateAccountBank(@Query("fName") String fName, @Query("phone") String phone, @Query("bankNumber") String bankNumber, @Query("bankAccount") String bankAccount,
                                             @Query("bankCode") String bankCode, @Query("bankBranch") String bankBranch);

    //api user info
    @POST("v2/PayCard/UserInfo")
    Call<UserInfo> getUserInfo();

    //api login with gmail
    @POST("v2/PayCard/DangNhapGoogle")
    Call<RegisterMDResult> loginWithGoogle(@Query("Token") String Token,
                                           @Query("FullName") String FullName, @Query("PassWord") String PassWord);

    //api create new passlv1
    @POST("v2/PayCard/DoiMatKhauCap1")
    Call<RegisterMDResult> createNewPassLv1(@Query("matKhauCu") String oldPass,
                                            @Query("matKhauMoi") String newPass);

    //api create new passlv2
    @POST("v2/PayCard/DoiMatKhauCap2")
    Call<RegisterMDResult> createNewPassLv2(@Query("matKhauCap1") String matKhauCap1,
                                            @Query("matkhauCap2") String matkhauCap2, @Query("matKhauMoiCap2") String matKhauMoiCap2);

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

    //api mua mã thẻ
    @POST("v2/PayCard/MuaMaThe")
    Call<ResultCardDoithe> buyCodeCard(@Query("nhaCungCap") int nhaCungCap,
                                       @Query("menhGia") String menhGia, @Query("soLuong") int soLuong,
                                       @Query("matKhauCap2") String matKhauCap2);

    //api nap tien dien thoai
    @POST("v2/PayCard/NapTienDienThoai")
    Call<ResultBanThe> payMoneyForMobile(@Query("msg") String msg, @Query("matKhauCap2") String matKhauCap2);

    //api pay card by bank by banthe24
    @POST("v2/PayCards/TelcoPay/GetCards")
    Call<ResultBanThe> payCodeCard(@Query("msg") String msg);

    //api pay card fast by banthe24
    @POST("v2/PayCards/TelcoPay/TopupMobile")
    Call<ResultBanThe> payCardFastBanThe(@Query("msg") String msg);

    //api pay card Viettel Slow by banthe24
    @POST("v2/PayCards/TelcoPay/TopupMobileVT")
    Call<ResultBanThe> payCardViettelSlowBanThe(@Query("msg") String msg);

    //api lich su chuyển tiền
    @POST("v2/PayCard/LichSuChuyenTien")
    Call<ResultHistory> historyTransfer(@Query("fromDate") String fromDate,
                                        @Query("toDate") String toDate,
                                        @Query("page") int page);

    //api lich su nhận tiền
    @POST("v2/PayCard/LichSuNhanTien")
    Call<ResultHistory> historyReceiver(@Query("fromDate") String fromDate,
                                        @Query("toDate") String toDate,
                                        @Query("page") int page);

    // api lich su rút tiền:
    @POST("v2/PayCard/LichSuRutTien")
    Call<ResultHistory> getHistoryMakeMoney(@Query("fromDate") String fromDate,
                                            @Query("toDate") String toDate, @Query("findKey") String findKey, @Query("status") String status,
                                            @Query("page") int page);
    //api lich su nap tien dien thoai:
    //    @POST("v2/PayCard/LichSuNapTienDT")
    //    Call<ResultHistory> getHistoryLoadMoneyFone(@Query("fromDate") String fromDate,
    //            @Query("toDate") String toDate, @Query("page") int page);

    // api lich su mua ma the:
    @POST("v2/PayCard/LichSuMuaMaThe")
    Call<ResultHistory> getHistoryBuyCard(@Query("fromDate") String fromDate,
                                          @Query("toDate") String toDate, @Query("findkey") String findkey, @Query("telcoCode") String telcoCode,
                                          @Query("page") int page);

    // api lich su nạp tiền đt:
    @POST("v2/PayCard/LichSuNapTienDienThoai")
    Call<ResultHistory> getHistoryPayPhone(@Query("fromDate") String fromDate,
                                           @Query("toDate") String toDate, @Query("findkey") String findkey, @Query("status") String status,
                                           @Query("page") int page);

    //api quen mat khau:
    @POST("v2/PayCard/LayLaiMatKhauCap1")
    Call<RegisterMDResult> getForgetPassWordByEmail(@Query("email") String email);

    //api quen mat khau cap 2:
    @POST("v2/PayCard/LayLaiMatKhauCap2")
    Call<RegisterMDResult> getForgetPassWordlv2(@Query("email") String email, @Query("matKhau") String matKhau);

    //api cap nhat thong tin tai khoan:
    @POST("v2/PayCard/CapNhatThongTinTK")
    Call<ResultJsonApi> updateAccountInfor(@Query("fName") String fName,
                                           @Query("phone") String phone, @Query("bankNumber") String bankNumber,
                                           @Query("bankAccount") String bankAccount, @Query("bankCode") String bankCode,
                                           @Query("bankBranch") String bankBranch);

    //api update nap tien dien thoai
    @POST("v2/PayCards/TelcoPay/UpdateNapSoDienThoai")
    Call<ResultBanThe> UpdatePayMoneyForMobile(@Query("msg") String msg);

    //List api chuyển tiền
    //1.api lấy về thông tin từ email
    @POST("v2/PayCard/TaiKhoanNhanTien")
    Call<UserInfo> getInfoAccountReceiver(@Query("email") String email);

    //2.api chuyển tiền
    @POST("v2/PayCard/ChuyenTien")
    Call<RegisterMDResult> transferMoney(@Query("money") String money, @Query("emailRecieve") String emailRecieve);

    //2.api xác thực chuyển tiền
    @POST("v2/PayCard/XacThucChuyenTien")
    Call<RegisterMDResult> transferMoneyAuthenticate(@Query("code") String code);

    //api rút tiền
    @POST("v2/PayCard/RutTien")
    Call<RegisterMDResult> makeMoney(@Query("sotien") String sotien, @Query("matKhauCap2") String matKhauCap2);
}
