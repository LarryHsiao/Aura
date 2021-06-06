package com.larryhsiao.aura.sercurity;

import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.larryhsiao.clotho.Action;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.Q;
import static androidx.biometric.BiometricManager.Authenticators.*;
import static androidx.biometric.BiometricPrompt.ERROR_CANCELED;
import static androidx.biometric.BiometricPrompt.ERROR_USER_CANCELED;
import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class BioAuth extends BiometricPrompt.AuthenticationCallback implements Action {
   public  interface Callback {
        void onSuccess();

        void onFailed(int code, String message);
    }

    private final Handler main = new Handler(Looper.getMainLooper());
    private final AppCompatActivity activity;
    private final String title;
    private final Callback callback;
    public BioAuth(AppCompatActivity activity, String title, Callback callback) {
        this.activity = activity;
        this.title = title;
        this.callback = callback;
    }

    @Override
    public void fire() {
        new BiometricPrompt(activity, newSingleThreadExecutor(), this)
            .authenticate(
                new BiometricPrompt.PromptInfo.Builder()
                    .setTitle(title)
                    .setAllowedAuthenticators(allowAuth())
                    .build()
            );
    }

    private int allowAuth() {
        if (SDK_INT > Q) {
            return DEVICE_CREDENTIAL | BIOMETRIC_STRONG;
        } else {
            return DEVICE_CREDENTIAL | BIOMETRIC_WEAK;
        }
    }

    @Override
    public void onAuthenticationError(int errorCode, @NotNull CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        if (errorCode != ERROR_USER_CANCELED && errorCode != ERROR_CANCELED) {
            main.post(() -> callback.onFailed(errorCode, errString.toString()));
        }
    }

    @Override
    public void onAuthenticationSucceeded(@NotNull BiometricPrompt.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        main.post(callback::onSuccess);
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        /*
         *   Ignore auth failed, the promote UI will inform the failure to user.
         *   The error will triggered as canceled by user which is the only event user can trigger.
         */
    }
}
