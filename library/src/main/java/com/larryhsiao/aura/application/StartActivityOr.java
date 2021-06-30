package com.larryhsiao.aura.application;

import android.app.Activity;
import android.content.Intent;
import com.larryhsiao.clotho.Action;

/**
 * Trying to start activity by given Intent, do the orAction if intent activity not available.
 */
public class StartActivityOr implements Action {
    private final Activity activity;
    private final Intent intent;
    private final Action orAction;

    public StartActivityOr(Activity activity, Intent intent, Action orAction) {
        this.activity = activity;
        this.intent = intent;
        this.orAction = orAction;
    }

    @Override
    public void fire() {
        if (intent.resolveActivity(activity.getPackageManager())== null){
            orAction.fire();
        }else{
            activity.startActivity(intent);
        }
    }
}
