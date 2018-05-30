package com.silverhetch.aura.view.fab;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import com.silverhetch.aura.R;

/**
 * Created by mikes on 9/24/2017.
 */

public class FabControlImpl implements FabControl {
    private final FloatingActionButton fabView;
    private FabBehavior fabBehavior;

    public FabControlImpl(FloatingActionButton fabView) {
        this.fabView = fabView;
        this.fabBehavior = null;
    }

    @Override
    public void attachFab(FabBehavior fabBehavior) {
        if (this.fabBehavior != null) {
            throw new RuntimeException("The previous fab not detach yet.");
        }
        this.fabBehavior = fabBehavior;
        if (fabView.getScaleY() == 1.0f) {
            showFabAfterDetachAnimation(fabBehavior);
        } else {
            showFab(fabBehavior);
        }
    }

    @Override
    public void detachFab(FabBehavior fabBehavior) {
        if (this.fabBehavior != fabBehavior) {
            throw new RuntimeException("fab not matched when detaching");
        }
        this.fabBehavior = null;
        fabView.animate()
                .scaleX(0).scaleY(0)
                .setDuration(fabView.getContext().getResources().getInteger(R.integer.animation_small))
                .start();
        fabView.setOnClickListener(null);
        fabView.setImageDrawable(null);
    }

    private void showFabAfterDetachAnimation(final FabBehavior fabBehavior) {
        fabView.postDelayed(new Runnable() {
            @Override
            public void run() {
                showFab(fabBehavior);
            }
        }, fabView.getContext().getResources().getInteger(R.integer.animation_delay_for_previous));
    }

    private void showFab(final FabBehavior fabBehavior) {
        fabView.animate()
                .scaleX(1.0f).scaleY(1.0f)
                .setDuration(fabView.getContext().getResources().getInteger(R.integer.animation_small))
                .start();
        fabView.setImageResource(fabBehavior.icon());
        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    fabBehavior.onClick();
                } catch (Exception e) {
                    Toast.makeText(fabView.getContext(), R.string.appError_unknown, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
