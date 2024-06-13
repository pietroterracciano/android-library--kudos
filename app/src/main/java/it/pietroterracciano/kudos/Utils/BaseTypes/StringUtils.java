package it.pietroterracciano.kudos.Utils.BaseTypes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.Charset;

import it.pietroterracciano.kudos.Constants.CStream;
import it.pietroterracciano.kudos.Utils.StreamUtils;

public abstract class StringUtils
{
    @Nullable
    public static String convert(@Nullable URI uri) { if(uri != null) try { return uri.toString(); } catch (Exception ignored) {} return null; }
    @Nullable
    public static String convert(@Nullable String s) {return s;}
    @Nullable
    public static String convert(@Nullable Integer i) {return i != null ? convert(i.intValue()) : null; }
    @Nullable
    public static String convert(@Nullable int i) { try { return String.valueOf(i); } catch (Exception ignored) {} return null;}
    @Nullable
    public static String convert(@Nullable Float f) {return f != null ? convert(f.floatValue()) : null; }
    @Nullable
    public static String convert(@Nullable float f) { try { return String.valueOf(f); } catch (Exception ignored) {} return null;}
    @Nullable
    public static String convert(@Nullable Double d) {return d != null ? convert(d.doubleValue()) : null; }
    @Nullable
    public static String convert(@Nullable double d) { try { return String.valueOf(d); } catch (Exception ignored) {} return null;}
    @Nullable
    public static String convert(@Nullable Character c) { return c != null ? convert(c.charValue()) : null; }
    @Nullable
    public static String convert(@Nullable char c) { try { return Character.toString(c); } catch (Exception ignored) {} return null; }

    @NonNull
    public static boolean isNull(@Nullable String s) { return s == null; }
    @NonNull
    public static boolean isEmpty(@Nullable String s) { return isNull(s) || s.isEmpty(); }
    @NonNull
    public static boolean isBlank(@Nullable String s) { return isNull(s) || s.trim().isEmpty(); }

    @Nullable
    public static String read(@Nullable InputStream is)
    {
        return read(StreamUtils.createInputStreamReader(is));
    }
    @Nullable
    public static String read(@Nullable InputStream is, @NonNull int iBufferSize)
    {
        return read(StreamUtils.createInputStreamReader(is), iBufferSize);
    }
    @Nullable
    public static String read(@Nullable InputStream is, @Nullable Charset ec)
    {
        return read(StreamUtils.createInputStreamReader(is, ec));
    }
    @Nullable
    public static String read(@Nullable InputStream is, @Nullable Charset ec, @NonNull int iBufferSize)
    {
        return read(StreamUtils.createInputStreamReader(is, ec), iBufferSize);
    }
    @Nullable
    public static String read(@Nullable Reader is)
    {
        return read(is, CStream.DefaultReadBufferSize);
    }
    @Nullable
    public static String read(@Nullable Reader is, @NonNull int iBufferSize)
    {
        if(is == null)
            return null;

        StringBuilder
            sb;

        int
            iBytesRead;

        if(iBufferSize < 1)
        {
            sb = new StringBuilder();

            try
            {
                while ( (iBytesRead  = is.read()) != -1)
                    sb.append(CharacterUtils.convert(iBytesRead));
            }
            catch (Exception ignored)
            {
                sb = null;
            }
        }
        else
        {
            if(!(is instanceof BufferedReader))
            {
                is = StreamUtils.createBufferedReader(is, iBufferSize);
                if(is == null)
                    return null;
            }

            sb = new StringBuilder();

            char[]
                ca = new char[iBufferSize];

            try
            {
                while ( (iBytesRead  = is.read(ca, 0, iBufferSize)) != -1)
                    sb.append(ca, 0, iBytesRead);
            }
            catch (Exception ignored)
            {
                sb = null;
            }
        }

        return
            sb != null
                ? sb.toString()
                : null;
    }
}