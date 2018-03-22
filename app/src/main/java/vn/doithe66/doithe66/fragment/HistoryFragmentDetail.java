package vn.doithe66.doithe66.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.text.ParseException;
import java.util.ArrayList;
import vn.doithe66.doithe66.R;
import vn.doithe66.doithe66.adapter.HistoryDetailAdapter;
import vn.doithe66.doithe66.model.DataResultHistory;

/**
 * Created by Windows 10 Now on 1/22/2018.
 */

public class HistoryFragmentDetail extends BaseFragment {
    public static final int REFRESH_DELAY = 2000;
    private RecyclerView rclHistory;
    private RecyclerView.LayoutManager mLayoutManager;
    private HistoryDetailAdapter mHistoryDetailAdapter;
    private ArrayList<DataResultHistory> lisHistory;

    private SharedPreferences sharedPreferences;
    private String token;
    private String sPrice;

    public HistoryFragmentDetail setDataForList(ArrayList<DataResultHistory> dataResultHistories) {
        HistoryFragmentDetail historyFragmentDetail = new HistoryFragmentDetail();
        if (dataResultHistories != null) {
            this.lisHistory = dataResultHistories;
        }
        return historyFragmentDetail;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_history_detail;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {

        rclHistory = mRoot.findViewById(R.id.rclHistory);
        setDataForList(lisHistory);
        setRecycleViewLayoutManager();
    }

    private void setRecycleViewLayoutManager() {
        mLayoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rclHistory.setLayoutManager(mLayoutManager);
        rclHistory.setHasFixedSize(true);
        mHistoryDetailAdapter = new HistoryDetailAdapter(getActivity(), lisHistory);
        rclHistory.setAdapter(mHistoryDetailAdapter);
    }
}
