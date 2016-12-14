package com.silverhetch.util.mock;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

/**
 * Created by Larry.H on 4/2/2018.
 */
public class ColorFragment extends Fragment {
    private static final int COLORS[] = {Color.BLACK, Color.YELLOW, Color.GRAY, Color.BLUE, Color.RED, Color.DKGRAY, Color.CYAN};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = new View(getActivity());
        final int index = new Object().hashCode() % COLORS.length;
        view.setBackgroundColor(COLORS[index]);
        return view;
    }
}
