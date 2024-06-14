package it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Descriptors;

import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.crypto.spec.SecretKeySpec;

import it.pietroterracciano.kudos.Constants.CCharacter;
import it.pietroterracciano.kudos.Constants.CClass;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricAlgorithm;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricCipherMode;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricPaddingMode;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Enums.ESymmetricKeySize;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Symmetricing;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Utils.SymmetricAlgorithmUtils;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Utils.SymmetricCipherModeUtils;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Utils.SymmetricKeySizeUtils;
import it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Utils.SymmetricPaddingModeUtils;
import it.pietroterracciano.kudos.Utils.Collections.ArrayUtils;
import it.pietroterracciano.kudos.Utils.Collections.NonPrimitives.BytesUtils;
import it.pietroterracciano.kudos.Utils.Collections.Primitives.bytesUtils;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public class TransientSymmetricingDescriptor
{
    private static final Constructor<SymmetricingDescriptor>
        __cnssd;

    static
    {
        __cnssd =
            ConstructorUtils.getDeclared
            (
                SymmetricingDescriptor.class,
                CClass.String,
                SecretKeySpec.class,
                CClass.Charset
            );
    }

    @Nullable
    public Charset Charset;
    @Nullable
    public ESymmetricAlgorithm Algorithm;
    @Nullable
    public ESymmetricCipherMode CipherMode;
    @Nullable
    public ESymmetricPaddingMode PaddingMode;
    @Nullable
    public String Key;
    @Nullable
    public ESymmetricKeySize KeySize;

    public SymmetricingDescriptor endize()
    {
        StringBuilder sb = new StringBuilder();

        String ssa = SymmetricAlgorithmUtils.convert(Algorithm);
        if(ssa != null) sb.append(ssa);

        String sscm= SymmetricCipherModeUtils.convert(CipherMode);
        if(sscm != null)
        {
            if(sb.length() > 0) sb.append(CCharacter.Slash);
            sb.append(sscm);
        }

        String sspm = SymmetricPaddingModeUtils.convert(PaddingMode);
        if(sspm != null)
        {
            if(sb.length() > 0) sb.append(CCharacter.Slash);
            sb.append(sspm);
        }

        byte[] bak = bytesUtils.convert(Key, StandardCharsets.UTF_8);
        Integer ib = SymmetricKeySizeUtils.convert(KeySize);

        if(ib != null)
            bak = bytesUtils.convert(ArrayUtils.resize(BytesUtils.convert(bak), ib/8));

        return ConstructorUtils.createInstance
        (
            __cnssd,
            sb.toString(),
            Symmetricing.convertToSecretKeySpec(bak, Algorithm),
            Charset
        );
    }

    private TransientSymmetricingDescriptor() {}

}