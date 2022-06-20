package org.autojs.autojs.ui.edit.toolbar;

import androidx.annotation.Nullable;

import org.autojs.autojs.R;

import java.util.Arrays;
import java.util.List;

public class NormalToolbarFragment extends ToolbarFragment {

    @Nullable
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_normal_toolbar;
    }

    @Override
    public List<Integer> getMenuItemIds() {
        return Arrays.asList(R.id.run, R.id.undo, R.id.redo, R.id.save);
    }
}
