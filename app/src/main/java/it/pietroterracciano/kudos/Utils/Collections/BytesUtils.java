package it.pietroterracciano.kudos.Utils.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import it.pietroterracciano.kudos.Constants.CStream;
import it.pietroterracciano.kudos.Utils.StreamUtils;

public abstract class BytesUtils
{
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
}