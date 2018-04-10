package vn.doithe66.doithe66.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.Utils.Utils;
import vn.doithe66.doithe66.model.HistoryDetail;
import vn.doithe66.doithe66.model.ItemCard;
import vn.doithe66.doithe66.view.HistoryFrmView;

import static vn.doithe66.doithe66.Utils.Constant.TRANSFER_MONEY;
import static vn.doithe66.doithe66.Utils.Constant.PAY_CARD_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.PAY_FAST_FRAGMENT;
import static vn.doithe66.doithe66.Utils.Constant.RECEIVER_MONEY;
import static vn.doithe66.doithe66.Utils.Constant.TAKE_MONEY;

/**
 * Created by Windows 10 Now on 1/20/2018.
 */

public class HistoryAdapter
        extends RecyclerView.Adapter<HistoryAdapter.HistoryTransactionViewHoder> {
    private SimpleDateFormat simpleDateFormat;
    private DatePickerDialog datePickerDialog;
    private OnClickOkItemHistory onClickOkItemHistory;
    private Context context;
    private String sFromDay = "";
    private String sToDay = "";
    private String sFindKey = "";
    private String positionSpinner = "";
    private Calendar calendar;
    private static int currentPos = 0;
    private ArrayList<HistoryDetail> lisHistory;

    public HistoryAdapter(Context context, ArrayList<HistoryDetail> lisHistory) {
        this.context = context;
        this.lisHistory = lisHistory;
    }

    public void setOnClickOkItemHistory(OnClickOkItemHistory onClickOkItemHistory) {
        this.onClickOkItemHistory = onClickOkItemHistory;
    }

    @Override
    public HistoryTransactionViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rcl_history, parent, false);
        calendar = Calendar.getInstance();
        return new HistoryTransactionViewHoder(view);
    }

    @Override
    public void onBindViewHolder(HistoryTransactionViewHoder holder, int position) {
        HistoryDetail historyDetail = lisHistory.get(position);
        holder.bindView(historyDetail, position);
    }

    @Override
    public int getItemCount() {
        return lisHistory.size();
    }

    public class HistoryTransactionViewHoder extends RecyclerView.ViewHolder
            implements HistoryFrmView {
        private TextView tvNameHistoryItem;
        private ImageView imgHistoryItem;
        private LinearLayout lnItemAllHistory;
        private LinearLayout lnItemExpandHistory;
        private TextView tvFromDayItemHistory;
        private TextView tvToDayItemHistory;
        private Button btnOkItemHstory;

        private LinearLayout lnFrDayItemHistory;
        private LinearLayout lnToDayItemHistory;

        private LinearLayout lnNumberphoneItemHistory;
        private ImageView imgGenaral;
        private EditText edtPhoneSlowItemHistory;

        private LinearLayout lnChosePriceItemHistory;

        private Spinner spnLoadmoneyItemHistory;
        private ArrayList<String> lisStringPriceItemHistory;
        private ArrayList<ItemCard> itemCards;
        //        private TextView tvPriceLoadmoneySlowItemHistory;

        public HistoryTransactionViewHoder(View itemView) {
            super(itemView);
            tvNameHistoryItem = itemView.findViewById(R.id.tv_namehistory_item);
            imgHistoryItem = itemView.findViewById(R.id.img_history_item);
            lnItemAllHistory = itemView.findViewById(R.id.ln_item_card_all_history);
            lnItemExpandHistory = itemView.findViewById(R.id.ln_item_expand_history);
            lnFrDayItemHistory = itemView.findViewById(R.id.ln_fr_day_item_history);
            lnToDayItemHistory = itemView.findViewById(R.id.ln_to_day_item_history);
            tvFromDayItemHistory = itemView.findViewById(R.id.tv_from_day_item_history);
            tvToDayItemHistory = itemView.findViewById(R.id.tv_to_day_item_history);
            btnOkItemHstory = itemView.findViewById(R.id.btn_ok_all_history);

            lnNumberphoneItemHistory = itemView.findViewById(R.id.ln_numberphone_item_history);
            edtPhoneSlowItemHistory = itemView.findViewById(R.id.edt_phone_loadmoney_item_history);
            lnChosePriceItemHistory = itemView.findViewById(R.id.ln_chose_price_item_history);
            spnLoadmoneyItemHistory = itemView.findViewById(R.id.spn_loadmoney_item_history);
            imgGenaral = itemView.findViewById(R.id.img_genaral);
            //            tvPriceLoadmoneySlowItemHistory = itemView.findViewById(R.id.tv_price_loadmoney_slow_item_history);

            lnFrDayItemHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDatePickerDialog(tvFromDayItemHistory, -1);
                }
            });

            lnToDayItemHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // khoi tao lai de check xem sFromday da co tt chua ?
                    sFromDay = tvFromDayItemHistory.getText().toString().trim();
                    if (sFromDay.isEmpty()) {
                        Toast.makeText(context, "Vui lòng chọn lịch: Từ ngày trước !",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        showDatePickerDialog(tvToDayItemHistory, calendar.getTime().getTime());
                    }
                }
            });
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            btnOkItemHstory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sFromDay = tvFromDayItemHistory.getText().toString().trim();
                    sToDay = tvToDayItemHistory.getText().toString().trim();

                    // khi ma nguoi dung an vao item co so dien thoai:
                    sFindKey = edtPhoneSlowItemHistory.getText().toString().trim();
                    if (sFromDay.isEmpty() || sToDay.isEmpty() || spnLoadmoneyItemHistory.getSelectedItemPosition() == 0) {
                        Toast.makeText(context, "Vui lòng điền đầy đủ và chọn đúng thông tin !",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        if (sFindKey.isEmpty()) {
                            sFindKey = "0";
                        }
                        Utils.closeKeyboard(context, edtPhoneSlowItemHistory.getWindowToken());
                        switch (getAdapterPosition()) {
                            case PAY_CARD_FRAGMENT:
                                positionSpinner = itemCards.get(spnLoadmoneyItemHistory.getSelectedItemPosition() - 1).getProviderCode();
                                break;
                            case PAY_FAST_FRAGMENT:
                                positionSpinner = String.valueOf(spnLoadmoneyItemHistory.getSelectedItemPosition() - 1);
                                break;
                            case TAKE_MONEY:
                                positionSpinner = String.valueOf(spnLoadmoneyItemHistory.getSelectedItemPosition());
                                break;
                            case RECEIVER_MONEY:
                                break;
                            case TRANSFER_MONEY:
                                break;
                        }
                        onClickOkItemHistory.onClickOk(getAdapterPosition(), sFromDay, sToDay,
                                sFindKey, positionSpinner);
                    }
                }
            });
        }

        private void bindView(HistoryDetail historyDetail, final int position) {
            itemCards = new ArrayList<>();
            itemCards = (ArrayList<ItemCard>) ConfigJson.getListTypeCard(SharedPrefs.getInstance().get(Constant.LIST_ITEM_CARD, String.class));
            tvNameHistoryItem.setText(historyDetail.getsTypeOfTransaction());
            Glide.with(context).load(historyDetail.getImage()).into(imgHistoryItem);
            lnItemAllHistory.setBackgroundResource(historyDetail.getColorItem());
            if (currentPos != position) {
                lnItemExpandHistory.setVisibility(View.GONE);
            } else {
                setDisplayItemHistory(position);
            }
            lnItemAllHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentPos = position;
                    setDisplayItemHistory(position);
                    notifyDataSetChanged();
                }
            });
        }

        private void setDisplayItemHistory(int adapterPosition) {
            switch (adapterPosition) {
                case PAY_CARD_FRAGMENT:
                    lnNumberphoneItemHistory.setVisibility(View.VISIBLE);
                    edtPhoneSlowItemHistory.setHint("Tìm serial...");
                    lnChosePriceItemHistory.setVisibility(View.VISIBLE);
                    lisStringPriceItemHistory = new ArrayList<>();
                    lisStringPriceItemHistory.add("Chọn nhà mạng");
                    for (int i = 0; i < itemCards.size(); i++) {
                        lisStringPriceItemHistory.add(itemCards.get(i).getNameHomeNetWork());
                    }
                    // su dung listview ko custom:
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_list_item_activated_1,
                            lisStringPriceItemHistory);
                    spnLoadmoneyItemHistory.setAdapter(stringArrayAdapter);
                    break;
                case PAY_FAST_FRAGMENT:
                    lnNumberphoneItemHistory.setVisibility(View.VISIBLE);
                    edtPhoneSlowItemHistory.setHint("Vui lòng nhập số điện thoại ...");
                    lnChosePriceItemHistory.setVisibility(View.VISIBLE);
                    ArrayList<String> listStatus = new ArrayList<>();
                    listStatus.add("Chọn trạng thái");
                    listStatus.add("Thất bại");
                    listStatus.add("Thành công");
                    ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_list_item_activated_1,
                            listStatus);
                    spnLoadmoneyItemHistory.setAdapter(statusAdapter);
                    break;
                case TAKE_MONEY:
                    lnNumberphoneItemHistory.setVisibility(View.VISIBLE);
                    imgGenaral.setImageResource(R.drawable.ic_counting_gray_50px);
                    edtPhoneSlowItemHistory.setHint("Nhập số tiền ...");
                    lnChosePriceItemHistory.setVisibility(View.VISIBLE);
                    ArrayList<String> listStatusMoney = new ArrayList<>();
                    listStatusMoney.add("Chọn trạng thái");
                    listStatusMoney.add("Đặt lệnh");
                    listStatusMoney.add("Đang chờ duyệt");
                    listStatusMoney.add("Đã chuyển");
                    ArrayAdapter<String> statusMoneyAdapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_list_item_activated_1,
                            listStatusMoney);
                    spnLoadmoneyItemHistory.setAdapter(statusMoneyAdapter);
                    break;
                case RECEIVER_MONEY:
                    lnNumberphoneItemHistory.setVisibility(View.GONE);
                    lnChosePriceItemHistory.setVisibility(View.GONE);
                    break;
                case TRANSFER_MONEY:
                    lnNumberphoneItemHistory.setVisibility(View.GONE);
                    lnChosePriceItemHistory.setVisibility(View.GONE);
                    break;
//                default:
//                    lnItemExpandHistory.setVisibility(View.GONE);
//                    Toast.makeText(context, "Đổi thẻ 66 hiện tại tạm ngừng cung cấp dịch vụ đổi thẻ cào sang tiền mặt", Toast.LENGTH_LONG).show();
//                    return;
            }
            Animation aSlideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
            lnItemExpandHistory.startAnimation(aSlideDown);
            lnItemExpandHistory.setVisibility(View.VISIBLE);
        }

        @Override
        public void onErrorPickPhone() {
            edtPhoneSlowItemHistory.setError("Bạn chưa nhập số điện thoại");
        }

        @Override
        public void toFrmHistoryDetail(String sMesagge) {

        }

        @Override
        public void onErorPickDate() {

        }

        private void showDatePickerDialog(final TextView txtDate, long minDate) {

            datePickerDialog =
                    new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {

                            calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            //kiem tra xem cai tv nao null :
                            txtDate.setText(simpleDateFormat.format(calendar.getTime()));
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
            if (minDate != -1) {
                datePickerDialog.getDatePicker().setMinDate(minDate - 1000);
            }
            datePickerDialog.show();
        }
    }

    public interface OnClickOkItemHistory {
        void onClickOk(int position, String frDate, String toDate, String sFindLey,
                       String positionSpinner);

    }

    private String getStringPriceOfSpinner(String s) {
        String newS = s.replace(".", "");
        String newS2 = newS.replace("đ", "");
        return newS2;
    }
}
