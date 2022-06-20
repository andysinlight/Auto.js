package org.autojs.autojs.ui.user;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stardust.app.OnActivityResultDelegate;

import org.autojs.autojs.R;
import org.autojs.autojs.ui.BaseActivity;
import org.autojs.autojs.ui.widget.EWebView;

/**
 * Created by Stardust on 2017/10/26.
 */
public class WebActivity extends BaseActivity implements OnActivityResultDelegate.DelegateHost {

    public static final String EXTRA_URL = "url";

    private OnActivityResultDelegate.Mediator mMediator = new OnActivityResultDelegate.Mediator();

    EWebView mEWebView;

    @Nullable
    @Override
    public int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void findView() {
        mEWebView = $(R.id.eweb_view);
    }

    @Override
    protected void setupView() {
        setToolbarAsBack(getIntent().getStringExtra(Intent.EXTRA_TITLE));
        mEWebView.getWebView().loadUrl(getIntent().getStringExtra(EXTRA_URL));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mMediator.onActivityResult(requestCode, resultCode, data);
    }

    @NonNull
    @Override
    public OnActivityResultDelegate.Mediator getOnActivityResultDelegateMediator() {
        return mMediator;
    }
}
