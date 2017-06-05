package com.havrylyuk.thenounproject.ui.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.havrylyuk.thenounproject.injection.component.ActivityFragmentComponent;

import butterknife.Unbinder;

/**
 * Created by Igor Havrylyuk on 20.05.2017.
 */

public abstract class BaseFragment extends Fragment
        implements BaseMvpView {

    private BaseActivity baseActivity;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.baseActivity = (BaseActivity) context;
        }
    }

    @Override
    public void showLoading() {
        if (baseActivity != null) {
            baseActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (baseActivity != null) {
            baseActivity.hideLoading();
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        if (baseActivity != null) {
            baseActivity.onError(resId);
        }
    }

    @Override
    public void onError(String message) {
        if (baseActivity != null) {
            baseActivity.onError(message);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return baseActivity != null && baseActivity.isNetworkConnected();
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    @Override
    public void hideKeyboard() {
        if (baseActivity != null) {
            baseActivity.hideKeyboard();
        }
    }

    public ActivityFragmentComponent getActivityFragmentComponent() {
        return baseActivity.getActivityFragmentComponent();
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        unbinder = unBinder;
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    protected abstract void init();

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

}
