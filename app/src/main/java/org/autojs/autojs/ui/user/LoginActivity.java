package org.autojs.autojs.ui.user;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.afollestad.materialdialogs.MaterialDialog;
import com.stardust.theme.ThemeColorManager;

import org.autojs.autojs.R;
import org.autojs.autojs.network.NodeBB;
import org.autojs.autojs.network.UserService;
import org.autojs.autojs.ui.BaseActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Stardust on 2017/9/20.
 */
public class LoginActivity extends BaseActivity {

    TextView mUserName;

    TextView mPassword;

    View mLogin;

    @Nullable
    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void findView() {
        mUserName = $(R.id.username);
        mPassword = $(R.id.password);
        mLogin = $(R.id.login);
        $(R.id.login, view -> login());
        $(R.id.forgot_password, view -> forgotPassword());
    }

    @Override
    protected void setupView() {
        setToolbarAsBack(getString(R.string.text_login));
        ThemeColorManager.addViewBackground(mLogin);
    }

    void login() {
        String userName = mUserName.getText().toString();
        String password = mPassword.getText().toString();
        if (!checkNotEmpty(userName, password)) {
            return;
        }
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .progress(true, 0)
                .content(R.string.text_logining)
                .cancelable(false)
                .show();
        UserService.getInstance().login(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), R.string.text_login_succeed, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        , error -> {
                            dialog.dismiss();
                            mPassword.setError(NodeBB.getErrorMessage(error, LoginActivity.this, R.string.text_login_fail));
                        });

    }

    void forgotPassword() {
        WebActivity.intent(this, WebActivity.class)
                .extra(WebActivity.EXTRA_URL, NodeBB.BASE_URL + "reset")
                .extra(Intent.EXTRA_TITLE, getString(R.string.text_reset_password))
                .start();
    }

    private boolean checkNotEmpty(String userName, String password) {
        if (userName.isEmpty()) {
            mUserName.setError(getString(R.string.text_username_cannot_be_empty));
            return false;
        }
        if (password.isEmpty()) {
            mUserName.setError(getString(R.string.text_password_cannot_be_empty));
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_register) {
            RegisterActivity.intent(this, RegisterActivity.class).start();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
