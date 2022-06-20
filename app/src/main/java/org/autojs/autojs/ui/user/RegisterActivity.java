package org.autojs.autojs.ui.user;

import android.util.Patterns;
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
 * Created by Stardust on 2017/10/26.
 */
public class RegisterActivity extends BaseActivity {

    TextView mEmail;

    TextView mUserName;

    TextView mPassword;

    View mRegister;

    @Nullable
    @Override
    public int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void findView() {
        mEmail = $(R.id.email);
         mUserName= $(R.id.username);
         mPassword= $(R.id.password);
         mRegister= $(R.id.register);
         $(R.id.register,view -> login());
    }

    @Override
    protected void setupView() {
        setToolbarAsBack(getString(R.string.text_register));
        ThemeColorManager.addViewBackground(mRegister);
    }

    void login() {
        String email = mEmail.getText().toString();
        String userName = mUserName.getText().toString();
        String password = mPassword.getText().toString();
        if (!validateInput(email, userName, password)) {
            return;
        }
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .progress(true, 0)
                .content(R.string.text_registering)
                .cancelable(false)
                .show();
        UserService.getInstance().register(email, userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            dialog.dismiss();
                            onRegisterResponse(response.string());
                        }
                        , error -> {
                            dialog.dismiss();
                            mPassword.setError(NodeBB.getErrorMessage(error, RegisterActivity.this, R.string.text_register_fail));
                        });

    }

    private void onRegisterResponse(String res) {
        Toast.makeText(this, R.string.text_register_succeed, Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean validateInput(String email, String userName, String password) {
        if (email.isEmpty()) {
            mEmail.setError(getString(R.string.text_email_cannot_be_empty));
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError(getString(R.string.text_email_format_error));
            return false;
        }
        if (userName.isEmpty()) {
            mUserName.setError(getString(R.string.text_username_cannot_be_empty));
            return false;
        }
        if (password.isEmpty()) {
            mUserName.setError(getString(R.string.text_password_cannot_be_empty));
            return false;
        }
        if (password.length() < 6) {
            mPassword.setError(getString(R.string.nodebb_error_change_password_error_length));
            return false;
        }
        return true;
    }
}
