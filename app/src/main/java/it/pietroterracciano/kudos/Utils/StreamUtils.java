package it.pietroterracciano.kudos.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import it.pietroterracciano.kudos.Constants.CStream;

public abstract class StreamUtils
{
    @NonNull
    public static boolean close(@Nullable Stream<?> str)
    {
        if(str != null)
            try { str.close(); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean close(@Nullable InputStream is)
    {
        if(is != null)
            try { is.close(); return true; } catch (Exception ignored) {}

        return false;
    }

    @NonNull
    public static boolean close(@Nullable OutputStream os)
    {
        if(os != null)
            try { os.close(); return true; } catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static boolean write(@Nullable OutputStream os, @Nullable byte[] ba)
    {
        if(os != null && ba != null)
            try { os.write(ba); return true; } catch (Exception ignored) {}
        return false;
    }

    @Nullable
    public static Integer getAvailable(@Nullable InputStream is)
    {
        if(is != null)
            try { return is.available(); } catch (Exception ignored) {}
        return null;
    }
    @Nullable
    public static InputStream createBufferedInputStream(@Nullable InputStream is)
    {
        return createBufferedInputStream(is, CStream.DefaultReadBufferSize);
    }
    @Nullable
    public static InputStream createBufferedInputStream(@Nullable InputStream is, @NonNull int iBufferSize)
    {
        if(is != null && iBufferSize > 0)
            try { return new BufferedInputStream(is, iBufferSize); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static OutputStream createBufferedOutputStream(@Nullable OutputStream os)
    {
        return createBufferedOutputStream(os, CStream.DefaultWriteBufferSize);
    }
    @Nullable
    public static OutputStream createBufferedOutputStream(@Nullable OutputStream os, @NonNull int iBufferSize)
    {
        if(os != null && iBufferSize > 0)
            try { return new BufferedOutputStream(os, iBufferSize); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Reader createInputStreamReader(@Nullable InputStream is)
    {
        return createInputStreamReader(is, StandardCharsets.UTF_8);
    }
    @Nullable
    public static Reader createInputStreamReader(@Nullable InputStream is, @Nullable Charset ec)
    {
        if(is != null && ec != null)
            try { return new InputStreamReader(is, ec); }
            catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Reader createBufferedReader(@Nullable InputStream is)
    {
        return createBufferedReader(createInputStreamReader(is));
    }
    @Nullable
    public static Reader createBufferedReader(@Nullable InputStream is, @NonNull int iBufferSize)
    {
        return createBufferedReader(createInputStreamReader(is), iBufferSize);
    }
    @Nullable
    public static Reader createBufferedReader(@Nullable InputStream is, @Nullable Charset ec)
    {
        return createBufferedReader(createInputStreamReader(is, ec));
    }
    @Nullable
    public static Reader createBufferedReader(@Nullable InputStream is, @Nullable Charset ec, @NonNull int iBufferSize)
    {
        return createBufferedReader(createInputStreamReader(is, ec), iBufferSize);
    }
    @Nullable
    public static Reader createBufferedReader(@Nullable Reader rdr)
    {
        return createBufferedReader(rdr, CStream.DefaultReadBufferSize);
    }
    @Nullable
    public static Reader createBufferedReader(@Nullable Reader rdr, @NonNull int iBufferSize)
    {
        if(rdr != null && iBufferSize > 0)
            try { return new BufferedReader(rdr, iBufferSize); }
            catch (Exception ignored) {}

        return null;
    }
}
