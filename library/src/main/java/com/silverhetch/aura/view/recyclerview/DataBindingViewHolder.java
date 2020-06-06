package com.silverhetch.aura.view.recyclerview;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by larryhsiao on 2017/6/1.
 */

public class DataBindingViewHolder extends RecyclerView.ViewHolder {
    private final Object viewDataBinding;

    public DataBindingViewHolder(View itemView, Object viewDataBinding) {
        super(itemView);
        this.viewDataBinding = viewDataBinding;
    }

    @SuppressWarnings("unchecked")
    public <T> T getViewDataBinding() {
        return (T) viewDataBinding;
    }

    public View rootView() {
        return itemView;
    }
}
