package org.autojs.autojs.ui.settings;

import android.annotation.SuppressLint;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.afollestad.materialdialogs.MaterialDialog;
import com.stardust.util.IntentUtil;
import com.tencent.bugly.crashreport.CrashReport;

import org.autojs.autojs.BuildConfig;
import org.autojs.autojs.R;
import org.autojs.autojs.theme.dialog.ThemeColorMaterialDialogBuilder;
import org.autojs.autojs.tool.IntentTool;
import org.autojs.autojs.ui.BaseActivity;

/**
 * Created by Stardust on 2017/2/2.
 */
public class AboutActivity extends BaseActivity {

    private static final String TAG = "AboutActivity";
    TextView mVersion;

    private int mLolClickCount = 0;

    @Nullable
    @Override
    public int getLayoutRes() {
        return R.layout.activity_about;
    }

    @Override
    protected void findView() {
        mVersion = $(R.id.version);
        $(R.id.github,view -> openGitHub());
        $(R.id.qq,view -> openQQToChatWithMe());
        $(R.id.email,view -> openEmailToSendMe());
        $(R.id.share,view -> share());
        $(R.id.icon,view -> lol());
        $(R.id.developer,view -> hhh());
    }

    @Override
    protected void setupView() {
        setVersionName();
        setToolbarAsBack(getString(R.string.text_about));
    }

    @SuppressLint("SetTextI18n")
    private void setVersionName() {
        mVersion.setText("Version " + BuildConfig.VERSION_NAME);
    }

    void openGitHub() {
        IntentTool.browse(this, getString(R.string.my_github));
    }

    void openQQToChatWithMe() {
        String qq = getString(R.string.qq);
        if (!IntentUtil.chatWithQQ(this, qq)) {
            Toast.makeText(this, R.string.text_mobile_qq_not_installed, Toast.LENGTH_SHORT).show();
        }
    }

    void openEmailToSendMe() {
        String email = getString(R.string.email);
        IntentUtil.sendMailTo(this, email);
    }

    void share() {
        IntentUtil.shareText(this, getString(R.string.share_app));
    }

    void lol() {
        mLolClickCount++;
        //Toast.makeText(this, R.string.text_lll, Toast.LENGTH_LONG).show();
        if (mLolClickCount >= 5) {
            crashTest();
            //showEasterEgg();
        }
    }

    private void showEasterEgg() {
        new MaterialDialog.Builder(this)
                .customView(R.layout.paint_layout, false)
                .show();
    }

    private void crashTest() {
        new ThemeColorMaterialDialogBuilder(this)
                .title("Crash Test")
                .positiveText("Crash")
                .onPositive((dialog, which) -> {
                    CrashReport.testJavaCrash();
                }).show();
    }

    void hhh() {
        Toast.makeText(this, R.string.text_it_is_the_developer_of_app, Toast.LENGTH_LONG).show();
    }
}
