package it.pietroterracciano.kudos.Constants;

import androidx.annotation.NonNull;

import it.pietroterracciano.kudos.Utils.DataTypes.StringUtils;

public abstract class CString
{
    @NonNull
    public static final String
        Slash = StringUtils.convert(CCharacter.Slash),
        DoubleDot = StringUtils.convert(CCharacter.DoubleDot),
        BackSlash = StringUtils.convert(CCharacter.BackSlash),
        Comma = StringUtils.convert(CCharacter.Comma),
        Empty = "",
        Zero = "0";
}
