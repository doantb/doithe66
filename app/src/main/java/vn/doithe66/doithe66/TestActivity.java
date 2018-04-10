//package vn.doithe66.doithe66;
//
//import android.Manifest;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.design.widget.CoordinatorLayout;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.ActivityCompat;
//import android.telephony.TelephonyManager;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import vn.doithe66.doithe66.activity.BaseActivity;
//
//public class TestActivity extends BaseActivity {
//    private static final int PERMISSION_REQUEST_READ_CONTACT = 0;
//    private static final int PERMISSION_REQUEST_CALL_PHONE = 1;
//    private BroadcastReceiver mMessageReceiver;
//    @BindView(R.id.click)
//    Button mClick;
//    @BindView(R.id.text)
//    TextView mText;
//    @BindView(R.id.view)
//    CoordinatorLayout mContainer;
//    Boolean isRegistered;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_test;
//    }
//
//    @Override
//    protected void initView(Bundle savedInstanceState) {
////        IntentFilter mFilter = new IntentFilter();
////        mFilter.addAction(USSDService.REFRESH_APP);
////        isRegistered = true;
////        mMessageReceiver = new BroadcastReceiver() {
////            @Override
////            public void onReceive(Context context, Intent intent) {
////                if (intent.getAction().equals(USSDService.REFRESH_APP)) {
////                    String message = intent.getStringExtra("message");
////                    Log.i("receiver", "Got message: " + message);
////                }
////            }
////        };
////        registerReceiver(mMessageReceiver, mFilter);
//    }
//    //Hàm contains để tìm chuỗi con s trong chuỗi hiện thời
//    //Ví dụ String s = "thanh cong"
//    //s.contains("thanh") -> trả về true
//    //s.contains("thah") -> trả về false
//    @Override
//    protected void onResume() {
//        super.onResume();
//        IntentFilter mFilter = new IntentFilter();
//        mFilter.addAction(USSDService.REFRESH_APP);
//        isRegistered = true;
//        mMessageReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                if (intent.getAction().equals(USSDService.REFRESH_APP)) {
//                    String message = intent.getStringExtra("message");
//                    Log.e("abc1", "Got message: " + message);
//                    Toast.makeText(getApplicationContext(), message+"", Toast.LENGTH_LONG).show();
//                }
//            }
//        };
//        registerReceiver(mMessageReceiver, mFilter);
//    }
//
//    @Override
//    protected void initVariables(Bundle savedInstanceState) {
//        ButterKnife.bind(this);
//        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
//                == PackageManager.PERMISSION_GRANTED)&&(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
//                == PackageManager.PERMISSION_GRANTED)) {
//            // Permission is already available, start camera preview
//            TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//            String mPhoneNumber = tMgr.getLine1Number();
//            Toast.makeText(this, mPhoneNumber + "", Toast.LENGTH_SHORT).show();
//        } else {
//            // Permission is missing and must be requested.
//            requestContactPermission();
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
////        try {
////            if (isRegistered) {
////                this.unregisterReceiver(mMessageReceiver);
////                isRegistered = false;
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//    @OnClick(R.id.click)
//    public void onViewClicked() {
//        requestUSSD("*100*" + Uri.encode("#"));
//    }
//
//    private void requestUSSD(String USSD) {
//
//        if (ActivityCompat.checkSelfPermission(TestActivity.this, Manifest.permission.CALL_PHONE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CALL_PHONE },
//                    PERMISSION_REQUEST_CALL_PHONE);
//            return;
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //API >= 26
//            TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//            manager.sendUssdRequest(USSD, new TelephonyManager.UssdResponseCallback() {
//                @Override
//                public void onReceiveUssdResponse(TelephonyManager telephonyManager, String request,
//                        CharSequence response) {
//                    super.onReceiveUssdResponse(telephonyManager, request, response);
//                    Toast.makeText(getApplicationContext(), "onReceiveUssdResponse()" + response,
//                            Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager,
//                        String request, int failureCode) {
//                    super.onReceiveUssdResponseFailed(telephonyManager, request, failureCode);
//                    Toast.makeText(getApplicationContext(),
//                            "onReceiveUssdResponseFailed()" + request, Toast.LENGTH_LONG).show();
//                }
//            }, new Handler());
//        } else {      //API < 26
//            startService(new Intent(this, USSDService.class));
//            dailNumber("*101#");
//        }
//    }
//
//    private void dailNumber(String s) {
//        String USSD = Uri.encode("*") + "101" + Uri.encode("#");
//        startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + USSD)));
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        mText.setText("USSD: " + requestCode + " " + resultCode + " " + data);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[],
//            int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CALL_PHONE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//            case PERMISSION_REQUEST_READ_CONTACT: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//    }
//
//    private void requestContactPermission() {
//        // Permission has not been granted and must be requested.
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.READ_CONTACTS)) {
//            // Provide an additional rationale to the user if the permission was not granted
//            // and the user would benefit from additional context for the use of the permission.
//            // Display a SnackBar with a button to request the missing permission.
//            Snackbar.make(mContainer, "Ứng dụng cần truy cập danh bạ", Snackbar.LENGTH_INDEFINITE)
//                    .setAction("Đồng ý", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            // Request the permission
//                            ActivityCompat.requestPermissions(TestActivity.this,
//                                    new String[] { Manifest.permission.READ_CONTACTS },
//                                    PERMISSION_REQUEST_READ_CONTACT);
//                        }
//                    })
//                    .show();
//        } else {
//            Snackbar.make(mContainer, "Quyền truy cập không có giá trị. Yêu cầu truy cập danh bạ.",
//                    Snackbar.LENGTH_SHORT).show();
//            // Request the permission. The result will be received in onRequestPermissionResult().
//            ActivityCompat.requestPermissions(this,
//                    new String[] { Manifest.permission.READ_CONTACTS },
//                    PERMISSION_REQUEST_READ_CONTACT);
//        }
//    }
//}
