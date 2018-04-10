package vn.doithe66.doithe66.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.text.ParseException;
import java.util.ArrayList;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.Utils.AutoLogin;
import vn.doithe66.doithe66.Utils.ConfigJson;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.Utils.SharedPrefs;
import vn.doithe66.doithe66.adapter.HistoryAdapter;
import vn.doithe66.doithe66.model.DataResultHistory;
import vn.doithe66.doithe66.model.HistoryDetail;
import vn.doithe66.doithe66.model.UserInfo;
import vn.doithe66.doithe66.presenter.HistoryFrmPresenter;
import vn.doithe66.doithe66.presenter.HistoryFrmPresenterImpl;
import vn.doithe66.doithe66.view.HistoryFrmView;

import static vn.doithe66.doithe66.Utils.Constant.KEY_TOKEN;

/**
 * Created by Windows 10 Now on 1/20/2018.
 */

public class HistoryTransactionFragment extends BaseFragment
        implements HistoryFrmView, HistoryAdapter.OnClickOkItemHistory {
    public static final String HISTORY_DETAIL = "history_detail";
    @BindView(R.id.recycle_history)
    RecyclerView rvHistory;
    //    @BindView(R.id.my_progess_bar)
    //    RelativeLayout mMyProgessBar;
    private ArrayList<HistoryDetail> lisHistory;
    private HistoryAdapter rvHistoryTransactionAdapter;
    private String token;
    private HistoryFrmPresenter mFrmPresenter;
    private ArrayList<DataResultHistory> mResultHistories;
    //chỉ cần check token ở activity xong put sang các fragment còn lại

    public static HistoryTransactionFragment newInstance(String token) {
        HistoryTransactionFragment historyTransactionFragment = new HistoryTransactionFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TOKEN, token);
        historyTransactionFragment.setArguments(args);
        return historyTransactionFragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {
        Bundle bundle = getArguments();
        if (bundle != null) {
            token = bundle.getString(KEY_TOKEN);
        }
        UserInfo mUserInfo = ConfigJson.getUserAccount(SharedPrefs.getInstance().get(Constant.USER_ACCOUNT, String.class));
        AutoLogin autoLogin = new AutoLogin(getActivity());
        if (!autoLogin.bLogin) {
            autoLogin.login(mUserInfo.getPassWord());
        }
        lisHistory = new ArrayList<>();
        //        mFrmPresenter = new HistoryFrmPresenterImpl(this, this);
        mFrmPresenter = new HistoryFrmPresenterImpl(this);
        mFrmPresenter.initDataHistory(lisHistory);
        rvHistoryTransactionAdapter = new HistoryAdapter(getActivity(), lisHistory);
        rvHistory.setAdapter(rvHistoryTransactionAdapter);
        rvHistory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvHistory.setLayoutManager(layoutManager);
        rvHistoryTransactionAdapter.setOnClickOkItemHistory(this);
    }

    @Override
    public void onErrorPickPhone() {

    }

    @Override
    public void toFrmHistoryDetail(String sMesagge) {
        HistoryFragmentDetail historyFragmentDetail = HistoryFragmentDetail.setDataForList(mResultHistories);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.right_in, R.anim.left_out);
        fragmentTransaction.replace(R.id.fr_main, historyFragmentDetail, HistoryFragmentDetail.class.getName());
        fragmentTransaction.addToBackStack("historyFragmentDetail");
        fragmentTransaction.commit();
        Toast.makeText(getActivity(), sMesagge, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErorPickDate() {

    }

    @Override
    public void onClickOk(int position, String frDate, String toDate, String sFoneItemHistory,
            String sPrice) {
        mResultHistories = new ArrayList<DataResultHistory>();
        mFrmPresenter.getDataForHistoryDetail(mResultHistories, position, frDate, toDate,
                sFoneItemHistory, sPrice, token);
    }

    //    @Override
    //    public void showProgess() {
    //        mMyProgessBar.setVisibility(View.VISIBLE);
    //    }
    //
    //    @Override
    //    public void hideProgess() {
    //        mMyProgessBar.setVisibility(View.GONE);
    //    }
}
