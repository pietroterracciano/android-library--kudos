package it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Builders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.nio.charset.Charset;

import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Descriptors.SymmetricingDescriptor;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Descriptors.TransientSymmetricingDescriptor;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricAlgorithm;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricCipherMode;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricKeySize;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricPaddingMode;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Symmetricing;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public final class SymmetricingBuilder
{
    private static final Constructor<Symmetricing>
        __cnss;
    private static final Constructor<TransientSymmetricingDescriptor>
        __cnstsd;

    static
    {
        __cnstsd = ConstructorUtils.getDeclared(TransientSymmetricingDescriptor.class);
        __cnss = ConstructorUtils.getDeclared(Symmetricing.class, SymmetricingDescriptor.class);
    }

    @NonNull
    private TransientSymmetricingDescriptor _sd;

    @NonNull
    public final SymmetricingBuilder setAlgorithm(@Nullable ESymmetricAlgorithm esa) {_sd.Algorithm = esa; return this; }
    @NonNull
    public final SymmetricingBuilder setCipherMode(@Nullable ESymmetricCipherMode escm) {_sd.CipherMode = escm; return this; }
    @NonNull
    public final SymmetricingBuilder setPaddingMode(@Nullable ESymmetricPaddingMode espm) {_sd.PaddingMode = espm; return this; }
    @NonNull
    public final SymmetricingBuilder setKey(@Nullable String sk) {_sd.Key = sk; return this; }
    @NonNull
    public final SymmetricingBuilder setCharset(@Nullable Charset ec) {_sd.Charset = ec; return this; }
    @NonNull
    public final SymmetricingBuilder setKeySize(@Nullable ESymmetricKeySize ks) {_sd.KeySize = ks; return this; }

    private SymmetricingBuilder() { _sd = ConstructorUtils.createInstance(__cnstsd); }

    @NonNull
    public Symmetricing build() { return ConstructorUtils.createInstance(__cnss, _sd.endize()); }
}