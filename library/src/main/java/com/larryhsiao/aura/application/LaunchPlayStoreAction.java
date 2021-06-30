package com.larryhsiao.aura.application;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.larryhsiao.clotho.Action;

import static android.content.Intent.ACTION_VIEW;

/**
 * Action to launch play store by given package.
 */
public class LaunchPlayStoreAction implements Action {
    private final Activity activity;
    private final String packageName;
    private final Action failureAction;

    public LaunchPlayStoreAction(Activity activity, Action orAction) {
        this(activity, activity.getPackageName(), orAction);
    }

    public LaunchPlayStoreAction(
        Activity activity,
        String packageName,
        Action failureAction
    ) {
        this.activity = activity;
        this.packageName = packageName;
        this.failureAction = failureAction;
    }

    @Override
    public void fire() {
        new StartActivityOr(
            activity,
            new Intent(ACTION_VIEW, Uri.parse("market://details?id=" + packageName)),
            new StartActivityOr(
                activity,
                new Intent(
                    ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)
                ), failureAction
            )
        ).fire();
    }
}
