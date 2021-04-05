package com.silverhetch.aura.view.activity;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import com.larryhsiao.clotho.Action;

/**
 * Action to make the Activity be able to shown on top of a screen.
 *
 * This action should be call in onCreate of Activity ot make effect.
 */
public class ShowOnLockScreen implements Action {
    private final Activity activity;

    public ShowOnLockScreen(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void fire() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            activity.setShowWhenLocked(true);
            activity.setTurnScreenOn(true);
            KeyguardManager keyguardManager = (KeyguardManager) activity.getSystemService(Context.KEYGUARD_SERVICE);
            keyguardManager.requestDismissKeyguard(activity, null);
        } else {
            activity.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            );
        }
    }
}
