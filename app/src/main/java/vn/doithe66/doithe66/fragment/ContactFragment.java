package vn.doithe66.doithe66.fragment;

import android.os.Bundle;
import android.view.View;
import java.text.ParseException;
import vn.doithe66.doithe66.R;

import static vn.doithe66.doithe66.Utils.Constant.KEY_TOKEN;

/**
 * Created by Windows 10 Now on 1/19/2018.
 */

public class ContactFragment extends BaseFragment {
    public static ContactFragment newInstance(String token) {
        ContactFragment contactFragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TOKEN, token);
        contactFragment.setArguments(args);
        return contactFragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void init(View mRoot, Bundle savedInstanceState) throws ParseException {

    }
}
