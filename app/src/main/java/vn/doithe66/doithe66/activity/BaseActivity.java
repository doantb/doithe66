package vn.doithe66.doithe66.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import vn.doithe66.doithe66.HomeActivity;
import vn.doithe66.doithe66.R;

/**
 * Created by Windows 10 Now on 12/28/2017.
 */

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        initVariables(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initVariables(Bundle savedInstanceState);

    protected void startActivity(Class<?> c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    public void setCancel(View imageView) {
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(HomeActivity.class);
                finishWithAnim();
            }
        });
    }

    public void setBack(View imageView) {
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseActivity.super.onBackPressed();
                finishWithAnim();
            }
        });
    }

    protected void hiddenInputType() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void onBackPressedCustom() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
            super.onBackPressed();
        }
    }

    public void startActivityWithAnim(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void finishWithAnim() {
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
