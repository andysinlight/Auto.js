package org.autojs.autojs.ui.doc;

import android.webkit.WebView;

import androidx.annotation.Nullable;

import org.autojs.autojs.Pref;
import org.autojs.autojs.R;
import org.autojs.autojs.ui.BaseActivity;
import org.autojs.autojs.ui.widget.EWebView;

/**
 * Created by Stardust on 2017/10/24.
 */
public class DocumentationActivity extends BaseActivity {

    public static final String EXTRA_URL = "url";

    EWebView mEWebView;

    WebView mWebView;

    @Nullable
    @Override
    public int getLayoutRes() {
        return R.layout.activity_documentation;
    }

    @Override
    protected void findView() {
        mEWebView = $(R.id.eweb_view);
    }

    @Override
    protected void setupView() {
        setToolbarAsBack(getString(R.string.text_tutorial));
        mWebView = mEWebView.getWebView();
        String url = getIntent().getStringExtra(EXTRA_URL);
        if (url == null) {
            url = Pref.getDocumentationUrl() + "index.html";
        }
        mWebView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
