package com.silverhetch.aura.view;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> m_views;
    private final View m_rootView;

    public ViewHolder(View views) {
        super(views);
        this.m_rootView = views;
        this.m_views = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = m_views.get(viewId);
        if (null == view) {
            view = itemView.findViewById(viewId);
            m_views.put(viewId, view);
        }
        return (T) view;
    }

    public TextView getTextView(@IdRes int iViewId) {
        return (TextView) getView(iViewId);
    }

    public EditText getEditText(@IdRes int iViewId) {
        return (EditText) getView(iViewId);
    }

    public ImageView getImageView(@IdRes int iViewId) {
        return (ImageView) getView(iViewId);
    }

    public SeekBar getSeekBar(@IdRes int iViewId) {
        return (SeekBar) getView(iViewId);
    }

    public ViewGroup getViewGroup(@IdRes int iViewId) {
        return (ViewGroup) getView(iViewId);
    }

    public View getRootView() {
        return m_rootView;
    }
}