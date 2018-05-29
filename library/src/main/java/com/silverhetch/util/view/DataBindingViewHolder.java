package com.silverhetch.util.view;

import android.support.v7.widget.RecyclerView;
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
