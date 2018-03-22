package vn.doithe66.doithe66;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.doithe66.doithe66.activity.BaseActivity;

/**
 * Created by Windows 10 Now on 1/2/2018.
 */

public class Test extends BaseActivity {
    @BindView(R.id.edt_text)
    EditText mEdtText;
    @BindView(R.id.btn_click)
    Button mBtnClick;

    @Override
    protected int getLayoutId() {
        return R.layout.test;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click)
    public void onViewClicked() {
    }
}
