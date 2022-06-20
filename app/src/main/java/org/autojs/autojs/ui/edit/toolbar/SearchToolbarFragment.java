package org.autojs.autojs.ui.edit.toolbar;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import org.autojs.autojs.R;

import java.util.Arrays;
import java.util.List;

public class SearchToolbarFragment extends ToolbarFragment {

    public static final String ARGUMENT_SHOW_REPLACE_ITEM = "show_replace_item";

    @Nullable
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_search_toolbar;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean showReplaceItem = getArguments().getBoolean(ARGUMENT_SHOW_REPLACE_ITEM, false);
        view.findViewById(R.id.replace).setVisibility(showReplaceItem ? View.VISIBLE : View.GONE);
    }

    @Override
    public List<Integer> getMenuItemIds() {
        return Arrays.asList(R.id.replace, R.id.find_next, R.id.find_prev, R.id.cancel_search);
    }

}
