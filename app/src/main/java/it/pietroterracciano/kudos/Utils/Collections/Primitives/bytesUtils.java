package it.pietroterracciano.kudos.Utils.Collections.Primitives;

import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import it.pietroterracciano.kudos.Constants.CStream;
import it.pietroterracciano.kudos.Utils.DataTypes.StringUtils;
import it.pietroterracciano.kudos.Utils.Collections.NonPrimitives.BytesUtils;
import it.pietroterracciano.kudos.Utils.StreamUtils;

public abstract class bytesUtils
{
    @Nullable
    public static byte[][] split(@Nullable Byte[] ba, @NonNull int i) { return split(convert(ba), i); }
    @Nullable
    public static byte[][] split(@Nullable byte[] ba, @NonNull int i)
    {
        Byte[][] ba0 = BytesUtils.split(ba, i);
        if(ba0 == null) return null;
        byte[][] ba1 = new byte[2][];
        ba1[0] = convert(ba0[0]);
        ba1[1] = convert(ba0[1]);
        return ba1;
    }
    @Nullable
    public static byte[] prepend(@Nullable Byte[] ba0, @Nullable byte[] ba1) { return convert(BytesUtils.prepend(ba0, ba1)); }
    @Nullable
    public static byte[] prepend(@Nullable byte[] ba0, @Nullable Byte[] ba1) { return convert(BytesUtils.prepend(ba0, ba1)); }
    @Nullable
    public static byte[] prepend(@Nullable Byte[] ba0, @Nullable Byte[] ba1) { return convert(BytesUtils.prepend(ba0, ba1)); }
    @Nullable
    public static byte[] prepend(@Nullable byte[] ba0, @Nullable byte[] ba1) { return convert(BytesUtils.prepend(ba0, ba1)); }
    @Nullable
    public static byte[] append(@Nullable Byte[] ba0, @Nullable byte[] ba1) { return convert(BytesUtils.append(ba0, ba1)); }
    @Nullable
    public static byte[] append(@Nullable byte[] ba0, @Nullable Byte[] ba1) { return convert(BytesUtils.append(ba0, ba1)); }
    @Nullable
    public static byte[] append(@Nullable Byte[] ba0, @Nullable Byte[] ba1) { return convert(BytesUtils.append(ba0, ba1)); }
    @Nullable
    public static byte[] append(@Nullable byte[] ba0, @Nullable byte[] ba1) { return convert(BytesUtils.append(ba0, ba1)); }

    @Nullable
    public static byte[] convert(@Nullable String s)
    {
        return convert(s, StandardCharsets.UTF_8);
    }
    @Nullable
    public static byte[] convert(@Nullable String s, @Nullable Charset ec)
    {
        if(s != null && ec != null)
            try { return s.getBytes(ec); } catch (Exception ignored) {}
        return null;
    }

    @Nullable
    public static byte[] convert(@Nullable Byte[] ba)
    {
        if(ba == null) return null;
        byte[] ba0 = new byte[ba.length];
        for(int i=0; i<ba0.length; i++) ba0[i] = byteUtils.convert(ba[i]);
        return ba0;
    }

    @Nullable
    public static byte[] read(@Nullable InputStream is)
    {
        return read(is, CStream.DefaultReadBufferSize);
    }
    @Nullable
    public static byte[] read(@Nullable InputStream is, @NonNull int iBufferSize)
    {
        if(is == null)
            return null;

        ByteArrayOutputStream
            baos;

        int
            iBytesRead;

        if(iBufferSize < 1)
        {
            baos = new ByteArrayOutputStream();

            try
            {
                while ( (iBytesRead  = is.read()) != -1)
                    baos.write(iBytesRead);
            }
            catch (Exception ignored)
            {
                baos = null;
            }
        }
        else
        {
            if(!(is instanceof BufferedInputStream))
            {
                is = StreamUtils.createBufferedInputStream(is, iBufferSize);
                if(is == null)
                    return null;
            }

            baos = new ByteArrayOutputStream();

            byte[]
                ba = new byte[iBufferSize];

            try
            {
                while ( (iBytesRead  = is.read(ba)) != -1)
                    baos.write(ba, 0, iBytesRead);
            }
            catch (Exception ignored)
            {
                baos = null;
            }
        }

        if(baos != null)
            try {return baos.toByteArray(); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static byte[] convertToBase64(@Nullable String s)
    {
        return convert(StringUtils.convertToBase64(s));
    }
    @Nullable
    public static byte[] convertToBase64(@Nullable String s, @Nullable Charset ec)
    {
        return convert(StringUtils.convertToBase64(s, ec));
    }
    @Nullable
    public static byte[] convertToBase64(@Nullable byte[] ba)
    {
        return convert(StringUtils.convertToBase64(ba));
    }

    @Nullable
    public static byte[] convertFromBase64(@Nullable String s)
    {
        return convertFromBase64(convert(s, StandardCharsets.UTF_8));
    }

    @Nullable
    public static byte[] convertFromBase64(@Nullable String s, @Nullable Charset ec)
    {
        return convertFromBase64(convert(s, ec));
    }

    @Nullable
    public static byte[] convertFromBase64(@Nullable byte[] ba)
    {
        if(ba != null)
            try { return Base64.decode(ba, Base64.DEFAULT);  }
            catch (Exception ignored) {}

        return null;
    }
}