package com.silverhetch.aura.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Adapter for representing empty list when there is no data.
 */
public class EmptyListAdapter extends RecyclerViewWrapper<ViewHolder> {
    private static final int ITEM_TYPE_EMPTY = 4102001;
    private final View emptyView;

    public EmptyListAdapter(
        @NotNull RecyclerView.Adapter<ViewHolder> origin, View emptyView) {
        super(origin);
        this.emptyView = emptyView;
    }

    @Override
    public int getItemCount() {
        int actualCount = super.getItemCount();
        if (actualCount == 0) {
            return 1;
        }
        return actualCount;
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        if (ITEM_TYPE_EMPTY != getItemViewType(position)) {
            super.onBindViewHolder(holder, position);
        }
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(
        @NotNull ViewGroup parent, int viewType) {
        if (ITEM_TYPE_EMPTY == viewType) {
            LinearLayout layout = new LinearLayout(parent.getContext());
            layout.setLayoutParams(
                new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            );
            layout.setGravity(CENTER);
            layout.addView(emptyView);
            return new ViewHolder(layout);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (super.getItemCount() == 0) {
            return ITEM_TYPE_EMPTY;
        }
        return super.getItemViewType(position);
    }
}
