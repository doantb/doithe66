package vn.doithe66.doithe66;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.doithe66.doithe66.Utils.Constant;
import vn.doithe66.doithe66.activity.BaseActivity;

/**
 * Created by Windows 10 Now on 1/12/2018.
 */

public class NotificationActivity extends BaseActivity {

    @BindView(R.id.web_view_notification)
    WebView mWebView;
    @BindView(R.id.notifi_img_back)
    ImageButton mNotifiImgBack;
    @BindView(R.id.notifi_txt_title_action_bar)
    TextView mHomeTxtTitleActionBar;
    @BindView(R.id.notifi_img_cancel)
    ImageView mNotifiImgCancel;
    @BindView(R.id.progess_bar_custom)
    ProgressBar mProgessBarCustom;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getIntExtra(Constant.FROM_PAY_BANK, 0) == 1) {
                mWebView.loadUrl(intent.getStringExtra(Constant.URL_PAY_BANK));
                mHomeTxtTitleActionBar.setText("Thanh toán bằng ngân hàng");
            } else {
                mWebView.loadUrl("https://doithe66.com/tin-tuc-1.html");
            }
        }
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        //        mWebView.setInitialScale(1);
        //        mWebView.getSettings().setLoadWithOverviewMode(true);
        //        mWebView.getSettings().setUseWideViewPort(true);
        //        mWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        //            mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        //        }
        mWebView.setWebChromeClient(new WebChromeClient());
        setCancel(mNotifiImgCancel);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgessBarCustom.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request,
                WebResourceError error) {
            super.onReceivedError(view, request, error);
            mProgessBarCustom.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }

        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }

    @OnClick(R.id.notifi_img_back)
    public void onViewClicked() {
        super.onBackPressed();
    }
}
