package it.pietroterracciano.kudos.Utils;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.regex.Pattern;

public final class ValidationUtils
{
    @NonNull
    public static boolean isValidMail(@Nullable CharSequence cs)
    {
        return
            !TextUtils.isEmpty(cs)
            && Patterns.EMAIL_ADDRESS.matcher(cs).matches();
    }

    @NonNull
    public static boolean isValidPhone(@Nullable CharSequence cs)
    {
        return
            !TextUtils.isEmpty(cs)
            && Patterns.PHONE.matcher(cs).matches();
    }
}
