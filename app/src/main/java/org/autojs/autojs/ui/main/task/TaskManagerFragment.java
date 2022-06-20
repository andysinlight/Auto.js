package org.autojs.autojs.ui.main.task;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.autojs.autojs.R;
import org.autojs.autojs.autojs.AutoJs;
import org.autojs.autojs.ui.main.ViewPagerFragment;
import org.autojs.autojs.ui.widget.SimpleAdapterDataObserver;

/**
 * Created by Stardust on 2017/3/24.
 */
public class TaskManagerFragment extends ViewPagerFragment {

    TaskListRecyclerView mTaskListRecyclerView;

    View mNoRunningScriptNotice;

    SwipeRefreshLayout mSwipeRefreshLayout;


    @Nullable
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_task_manager;
    }

    @Override
    protected void findView() {
         mTaskListRecyclerView=$(R.id.task_list);
         mNoRunningScriptNotice=$(R.id.notice_no_running_script);
         mSwipeRefreshLayout=$(R.id.swipe_refresh_layout);
    }

    public TaskManagerFragment() {
        super(45);
        setArguments(new Bundle());
    }

    protected void setUpViews() {
        init();
        final boolean noRunningScript = mTaskListRecyclerView.getAdapter().getItemCount() == 0;
        mNoRunningScriptNotice.setVisibility(noRunningScript ? View.VISIBLE : View.GONE);
    }

    private void init() {
        mTaskListRecyclerView.getAdapter().registerAdapterDataObserver(new SimpleAdapterDataObserver() {

            @Override
            public void onSomethingChanged() {
                final boolean noRunningScript = mTaskListRecyclerView.getAdapter().getItemCount() == 0;
                mTaskListRecyclerView.postDelayed(() -> {
                    if (mNoRunningScriptNotice == null)
                        return;
                    mNoRunningScriptNotice.setVisibility(noRunningScript ? View.VISIBLE : View.GONE);
                }, 150);
            }

        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mTaskListRecyclerView.refresh();
            mTaskListRecyclerView.postDelayed(() -> {
                if (mSwipeRefreshLayout != null)
                    mSwipeRefreshLayout.setRefreshing(false);
            }, 800);
        });
    }

    @Override
    protected void onFabClick(FloatingActionButton fab) {
        AutoJs.getInstance().getScriptEngineService().stopAll();
    }

    @Override
    public boolean onBackPressed(Activity activity) {
        return false;
    }
}
