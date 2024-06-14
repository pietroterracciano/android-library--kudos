package it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Builders.SymmetricingBuilder;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Descriptors.SymmetricingDescriptor;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricAlgorithm;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Utils.SymmetricAlgorithmUtils;
import it.pietroterracciano.kudos.Modules.JSONingModule.JSONing;
import it.pietroterracciano.kudos.Utils.DataTypes.StringUtils;
import it.pietroterracciano.kudos.Utils.Collections.Primitives.bytesUtils;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public final class Symmetricing
{
    @Nullable
    private static final SecureRandom
        __sr;
    @NonNull
    private static final Constructor<SymmetricingBuilder>
        __cnssb;

    static
    {
        __cnssb = ConstructorUtils.getDeclared(SymmetricingBuilder.class);
        SecureRandom sr;
        try { sr = new SecureRandom(); } catch (Exception ignored) {sr = null;}
        __sr = sr;
    }

    @Nullable
    public static final byte[] generateIV()
    {
        if(__sr != null)
            try
            {
                byte[] ba = new byte[16];
                __sr.nextBytes(ba);
                return ba;
            }
            catch (Exception ignored)
            {
            }

        return null;
    }

    @Nullable
    public static final IvParameterSpec convertToIVParameterSpec(@Nullable byte[] ba)
    {
        if(ba != null)
            try { return new IvParameterSpec(ba); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static final IvParameterSpec generateIvParameterSpec()
    {
        return convertToIVParameterSpec(generateIV());
    }

    @Nullable
    public static final byte[] generateKey(@Nullable ESymmetricAlgorithm esa)
    {
        String ssa = SymmetricAlgorithmUtils.convert(esa);

        if(ssa != null)
            try { KeyGenerator.getInstance(ssa).generateKey().getEncoded(); }
            catch (Exception ignored) { return null; }

        return null;
    }

    @Nullable
    public static final SecretKeySpec convertToSecretKeySpec(@Nullable byte[] ba, @Nullable ESymmetricAlgorithm esa)
    {
        if(ba == null) return null;
        String ssa = SymmetricAlgorithmUtils.convert(esa);
        if(ssa == null) return null;
        try { return new SecretKeySpec(ba, ssa); }
        catch (Exception ignored) { return null; }
    }

    @Nullable
    public static final SecretKeySpec generateSecretKeySpec(@Nullable ESymmetricAlgorithm esa)
    {
        return convertToSecretKeySpec(generateKey(esa), esa);
    }

    @NonNull
    public static final SymmetricingBuilder requireBuilder()
    {
        return ConstructorUtils.createInstance(__cnssb);
    }

    @NonNull
    public final SymmetricingDescriptor _sd;

    private Symmetricing
    (
        @NonNull SymmetricingDescriptor sd
    )
    {
        _sd = sd;
    }

    @Nullable
    public String encrypt(@Nullable Object o) { return encrypt(o, JSONing.Default); }
    @Nullable
    public String encrypt(@Nullable Object o, @Nullable JSONing jsoning)
    {
        return jsoning != null
            ? encrypt(jsoning.serialize(o))
            : null;
    }
    @Nullable
    public String encrypt(@Nullable String s)
    {
        if(!_sd.IsComplete) return null;

        byte[] baIn = _bytesUtils_convert(s);
        if(baIn == null) return null;

        Cipher cph = _Cipher_getInstance();
        if(cph == null) return null;

        IvParameterSpec ivps = generateIvParameterSpec();
        if(!_Cipher_init(cph, Cipher.ENCRYPT_MODE, ivps)) return null;

        byte[] baOut = _Cipher_doFinal(cph, baIn);
        if(baOut == null) return null;

        return
            StringUtils.convertToBase64
            (
                bytesUtils.prepend
                (
                    baOut,
                    ivps.getIV()
                )
            );
    }

    @Nullable
    public <T> T decrypt(@Nullable Type t, @Nullable String s) { return decrypt(t, s, JSONing.Default); }
    @Nullable
    public <T> T decrypt(@Nullable Type t, @Nullable String s, @Nullable JSONing jsoning)
    {
        return jsoning != null
                ? jsoning.deserialize(t, decrypt(s))
                : null;
    }
    @Nullable
    public <T> T decrypt(@Nullable TypeToken<T> tt, @Nullable String s) { return decrypt(tt, s, JSONing.Default); }
    @Nullable
    public <T> T decrypt(@Nullable TypeToken<T> tt, @Nullable String s, @Nullable JSONing jsoning)
    {
        return jsoning != null
                ? jsoning.deserialize(tt, decrypt(s))
                : null;
    }
    @Nullable
    public <T> T decrypt(@Nullable Class<T> cls, @Nullable String s) { return decrypt(cls, s, JSONing.Default); }
    @Nullable
    public <T> T decrypt(@Nullable Class<T> cls, @Nullable String s, @Nullable JSONing jsoning)
    {
        return jsoning != null
            ? jsoning.deserialize(cls, decrypt(s))
            : null;
    }
    @Nullable
    public String decrypt(@Nullable String s)
    {
        if(!_sd.IsComplete)
            return null;

        byte[][]
            baaOut =
                bytesUtils.split
                (
                    _bytesUtils_convertFromBase64(s),
                    16
                );

        if(baaOut == null)
            return null;

        Cipher cph = _Cipher_getInstance();
        if(cph == null) return null;

        IvParameterSpec ivps = convertToIVParameterSpec(baaOut[0]);
        if(!_Cipher_init(cph, Cipher.DECRYPT_MODE, ivps)) return null;

        return
            StringUtils.convert
            (
                _Cipher_doFinal
                (
                    cph,
                    baaOut[1]
                )
            );
    }

    @Nullable
    private byte[] _Cipher_doFinal(@Nullable Cipher cph, @Nullable byte[] ba)
    {
        if(cph != null && ba != null)
            try { return cph.doFinal(ba); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    private byte[] _bytesUtils_convertFromBase64(@Nullable String s)
    {
        return _sd.Charset != null
            ? bytesUtils.convertFromBase64(s, _sd.Charset)
            : bytesUtils.convertFromBase64(s);
    }

    @Nullable
    private byte[] _bytesUtils_convert(@Nullable String s)
    {
        return _sd.Charset != null
            ? bytesUtils.convert(s, _sd.Charset)
            : bytesUtils.convert(s);
    }

    @Nullable
    private Cipher _Cipher_getInstance()
    {
        try { return Cipher.getInstance(_sd.Transformation); }
        catch (Exception ignored) { return null; }
    }

    @Nullable
    private boolean _Cipher_init(@Nullable Cipher cph, @NonNull int i, @Nullable IvParameterSpec ivps)
    {
        if(cph != null && ivps != null)
            try { cph.init(i, _sd.SecretKeySpec, ivps); return true; } catch (Exception ignored) {}

        return false;
    }
}
