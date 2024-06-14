package it.pietroterracciano.kudos.Modules.CryptingModule.SymmetricingModule.Constants;

public final class CSymmetricPaddingMode
{
    public static final String
        None = "NoPadding",
        PKCS5 = "PKCS5Padding",
        ISO10126 = "ISO10126Padding",
        OAEP = "OAEPPadding",
        PKCS1 = "PKCS1Padding",
        OAEP_SHA1_MGF1 = "OAEPwithSHA-1andMGF1Padding",
        OAEP_SHA224_MGF1 = "OAEPwithSHA-224andMGF1Padding",
        OAEP_SHA256_MGF1 = "OAEPwithSHA-256andMGF1Padding",
        OAEP_SHA384_MGF1 = "OAEPwithSHA-384andMGF1Padding",
        OAEP_SHA512_MGF1 = "OAEPwithSHA-512andMGF1Padding";
}
