/*
 * Copyright (C) 2013-2021 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.b3dgs.lionengine;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Tools related to files and directories handling.
 * <p>
 * This class is Thread-Safe.
 * </p>
 */
public final class UtilStream
{
    /** Error temporary file. */
    static final String ERROR_TEMP_FILE = "Unable to create temporary file for: ";
    /** Temporary directory. */
    static final String TEMP_DIR = "java.io.tmpdir";
    /** Temp file created. */
    private static final String TEMP_FILE_CREATED = "Temp file created: ";
    /** Copy buffer. */
    private static final int BUFFER_COPY = 65_535;

    /**
     * Copy a stream onto another.
     * 
     * @param source The source stream (must not be <code>null</code>).
     * @param destination The destination stream (must not be <code>null</code>).
     * @throws IOException If error.
     * @throws LionEngineException If invalid arguments.
     */
    public static void copy(InputStream source, OutputStream destination) throws IOException
    {
        Check.notNull(source);
        Check.notNull(destination);

        final byte[] buffer = new byte[BUFFER_COPY];
        while (true)
        {
            final int read = source.read(buffer);
            if (read == -1)
            {
                break;
            }
            destination.write(buffer, 0, read);
        }
    }

    /**
     * Get of full copy of the input stream stored in a temporary file.
     * 
     * @param name The file name reference, to have a similar temporary file name (must not be <code>null</code>).
     * @param input The input stream reference (must not be <code>null</code>).
     * @return The temporary file created with copied content from stream.
     * @throws LionEngineException If invalid arguments or invalid stream.
     */
    public static File getCopy(String name, InputStream input)
    {
        Check.notNull(name);
        Check.notNull(input);

        final String prefix;
        final String suffix;
        final int minimumPrefix = 3;
        final int i = name.lastIndexOf(Constant.DOT);
        if (i > minimumPrefix)
        {
            prefix = name.substring(0, i);
            suffix = name.substring(i);
        }
        else
        {
            if (name.length() > minimumPrefix)
            {
                prefix = name;
            }
            else
            {
                prefix = "temp";
            }
            suffix = null;
        }
        try
        {
            final File temp = File.createTempFile(prefix, suffix);
            Verbose.info(TEMP_FILE_CREATED + temp.getAbsolutePath());
            try (OutputStream output = new BufferedOutputStream(new FileOutputStream(temp)))
            {
                copy(input, output);
            }
            return temp;
        }
        catch (final IOException exception)
        {
            throw new LionEngineException(exception, ERROR_TEMP_FILE + name);
        }
    }

    /**
     * Private constructor.
     */
    private UtilStream()
    {
        throw new LionEngineException(LionEngineException.ERROR_PRIVATE_CONSTRUCTOR);
    }
}
