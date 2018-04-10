package vn.doithe66.doithe66.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import java.text.ParseException;

/**
 * Created by Windows 10 Now on 1/3/2018.
 */

public abstract class BaseFragment extends Fragment {
    private View mRoot;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(layoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mRoot);
        try {
            init(mRoot, savedInstanceState);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mRoot;
    }

    protected abstract int layoutId();

    protected abstract void init(View mRoot, Bundle savedInstanceState) throws ParseException;

    protected void startFragment(Fragment fragment) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        mUnbinder.unbind();
    }

    //    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        RefWatcher refWatcher = App.getRefWatcher(getActivity());
//        refWatcher.watch(this);
//    }
    public void hiddenInputType() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}