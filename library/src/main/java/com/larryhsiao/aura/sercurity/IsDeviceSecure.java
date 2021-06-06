package com.larryhsiao.aura.sercurity;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import com.larryhsiao.clotho.Source;

import static androidx.core.content.ContextCompat.getSystemService;

public class IsDeviceSecure implements Source<Boolean> {
    private final Context context;

    public IsDeviceSecure(Context context) {
        this.context = context;
    }

    @Override
    public Boolean value() {
        final KeyguardManager manager = getSystemService(context, KeyguardManager.class);
        if (manager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return manager.isDeviceSecure();
            } else {
                return manager.isKeyguardSecure();
            }
        } else {
            return false;
        }
    }
}
