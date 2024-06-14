package it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Descriptors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.charset.Charset;

import javax.crypto.spec.SecretKeySpec;

import it.pietroterracciano.kudos.Utils.DataTypes.StringUtils;

public final class SymmetricingDescriptor
{
    @Nullable
    public final SecretKeySpec SecretKeySpec;
    @Nullable
    public final String Transformation;
    @Nullable
    public final Charset Charset;
    @NonNull
    public final Boolean IsComplete;

    private SymmetricingDescriptor
    (
        @Nullable String st,
        @Nullable SecretKeySpec sks,
        @Nullable Charset chr
    )
    {
        Transformation = st;
        SecretKeySpec = sks;
        Charset = chr;

        IsComplete =
            !StringUtils.isEmpty(Transformation)
            && (SecretKeySpec) != null;
    }
}
