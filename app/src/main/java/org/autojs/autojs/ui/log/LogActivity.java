package org.autojs.autojs.ui.log;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.stardust.autojs.core.console.ConsoleImpl;
import com.stardust.autojs.core.console.ConsoleView;

import org.autojs.autojs.R;
import org.autojs.autojs.autojs.AutoJs;
import org.autojs.autojs.ui.BaseActivity;

public class LogActivity extends BaseActivity {

    ConsoleView mConsoleView;

    private ConsoleImpl mConsoleImpl;

    @Nullable
    @Override
    public int getLayoutRes() {
        return R.layout.activity_log;
    }

    @Override
    protected void findView() {
        mConsoleView = $(R.id.console);
        $(R.id.fab, view -> clearConsole());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyDayNightMode();
    }


    @Override
    protected void setupView() {
        setToolbarAsBack(getString(R.string.text_log));
        mConsoleImpl = AutoJs.getInstance().getGlobalConsole();
        mConsoleView.setConsole(mConsoleImpl);
        mConsoleView.findViewById(R.id.input_container).setVisibility(View.GONE);
    }

    void clearConsole() {
        mConsoleImpl.clear();
    }
}
