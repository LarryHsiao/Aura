package com.silverhetch.aura.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.silverhetch.clotho.Source;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Adapter for representing empty list when there is no data.
 *
 * Note: The origin should use notify range functions with caution which will cause crash.
 * e.g. When adding new data into empty data set, use notifyDataSetChanged to prevent
 * notify item insertion to a exist item on View.
 */
public final class EmptyListAdapter extends RecyclerViewWrapper<ViewHolder> {
    private static final int ITEM_TYPE_EMPTY = 4102001;
    private final Source<View> emptyView;

    public EmptyListAdapter(
        @NotNull RecyclerView.Adapter<ViewHolder> origin,
        Source<View> emptyView) {
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
            layout.addView(emptyView.value());
            return new ViewHolder(layout);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(
        @NotNull ViewHolder holder, int position,
        @NotNull List<Object> payloads) {
        if (ITEM_TYPE_EMPTY == getItemViewType(position)) {
            onBindViewHolder(holder, position);
        } else {
            super.onBindViewHolder(holder, position, payloads);
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
