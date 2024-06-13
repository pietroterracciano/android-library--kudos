package it.pietroterracciano.kudos.Utils;

import androidx.annotation.Nullable;

import java.net.URI;
import java.util.Arrays;

import it.pietroterracciano.kudos.Constants.CCharacter;
import it.pietroterracciano.kudos.Constants.CStream;
import it.pietroterracciano.kudos.Constants.CString;
import it.pietroterracciano.kudos.Utils.BaseTypes.StringUtils;
import it.pietroterracciano.kudos.Utils.Collections.ArrayUtils;

public final class URIUtils
{
    @Nullable
    public static String extract(@Nullable String s)
    {
        if(s == null) return null;

        while(s.startsWith(CString.Slash))
            s = s.substring(1);

        while(s.endsWith(CString.Slash))
            s = s.substring(0, s.length() - 1);

        return s;
    }

    @Nullable
    public static URI concat(@Nullable URI uri, @Nullable String ... sa)
    {
        if(sa == null) return null;

        String[] sa0 = new String[sa.length+1];
        sa0[0] = StringUtils.convert(uri);
        for(int i=0; i<sa.length; i++) sa0[i+1] = sa[i];

        return concat(sa0);
    }

    @Nullable
    public static URI concat(@Nullable String... sa)
    {
        if(sa == null) return null;

        StringBuilder sb = new StringBuilder(sa.length);
        String sai;
        boolean b;
        for(int i=0; i<sa.length; i++)
        {
            sai = extract(sa[i]);
            if(StringUtils.isBlank(sai)) continue;
            sb.append(sai);

            if(i < sa.length - 1)
                sb
                    .append(CCharacter.Slash);
        }

        String s = sb.toString();
        s = s.replaceFirst(CString.Slash+CString.DoubleDot, CString.DoubleDot+CString.Slash);
        s = s.replaceAll(CString.Slash+CString.DoubleDot+CString.Slash, CString.DoubleDot);
        s = s.replaceAll(CString.Slash+CString.DoubleDot, CString.DoubleDot);

        return convert(s);
    }

    @Nullable
    public static URI convert(@Nullable String s)
    {
        if(!StringUtils.isBlank(s))
            try { return new URI(s); } catch (Exception ignored) {}

        return null;
    }
}