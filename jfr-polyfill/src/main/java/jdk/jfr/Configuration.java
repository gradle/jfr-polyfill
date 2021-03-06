/*
 * Copyright (c) 2016, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package jdk.jfr;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;

/**
 * A collection of settings and metadata describing the configuration.
 *
 * @since 8
 */
public final class Configuration {
    private final Map<String, String> settings = Collections.emptyMap();

    // package private
    Configuration() {
    }

    /**
     * Returns the settings that specifies how a recording is configured.
     * <p>
     * Modifying the returned {@code Map} object doesn't change the
     * configuration.
     *
     * @return settings, not {@code null}
     */
    public Map<String, String> getSettings() {
        return new LinkedHashMap<String, String>(settings);
    }

    /**
     * Returns an identifying name (for example, {@code "default" or "profile")}.
     *
     * @return the name, or {@code null} if it doesn't exist
     */
    public String getName() {
        return "";
    }

    /**
     * Returns a human-readable name (for example, {@code "Continuous" or "Profiling"}}.
     *
     * @return the label, or {@code null} if it doesn't exist
     */
    public String getLabel() {
        return "";
    }

    /**
     * Returns a short sentence that describes the configuration (for example
     * {@code "Low
     * overhead configuration safe for continuous use in production
     * environments"})
     *
     * @return the description, or {@code null} if it doesn't exist
     */
    public String getDescription() {
        return "";
    }

    /**
     * Returns who created the configuration (for example {@code "OpenJDK"}).
     *
     * @return the provider, or {@code null} if it doesn't exist
     */
    public String getProvider() {
        return "";
    }

    /**
     * Returns a textual representation of the configuration (for example, the
     * contents of a JFC file).
     *
     * @return contents, or {@code null} if it doesn't exist
     *
     * @see Configuration#getContents()
     */
    public String getContents() {
        return "";
    }

    /**
     * Reads a configuration from a file.
     *
     * @param path the file that contains the configuration, not {@code null}
     * @return the read {@link Configuration}, not {@code null}
     * @throws ParseException if the file can't be parsed
     * @throws IOException if the file can't be read
     * @throws SecurityException if a security manager exists and its
     *         {@code checkRead} method denies read access to the file.
     *
     * @see java.io.File#getPath()
     * @see java.lang.SecurityManager#checkRead(java.lang.String)
     */
    public static Configuration create(Path path) throws IOException, ParseException {
        Objects.requireNonNull(path);
        return new Configuration();
    }

    /**
     * Reads a configuration from a character stream.
     *
     * @param reader a {@code Reader} that provides the configuration contents, not
     *        {@code null}
     * @return a configuration, not {@code null}
     * @throws IOException if an I/O error occurs while trying to read contents
     *         from the {@code Reader}
     * @throws ParseException if the file can't be parsed
     */
    public static Configuration create(Reader reader) throws IOException, ParseException {
        Objects.requireNonNull(reader);
        return new Configuration();
    }

    /**
     * Returns a predefined configuration.
     * <p>
     * See {@link Configuration#getConfigurations()} for available configuration
     * names.
     *
     * @param name the name of the configuration (for example, {@code "default"} or
     *        {@code "profile"})
     * @return a configuration, not {@code null}
     *
     * @throws IOException if a configuration with the given name does not
     *         exist, or if an I/O error occurs while reading the
     *         configuration file
     * @throws ParseException if the configuration file can't be parsed
     */
    public static Configuration getConfiguration(String name) throws IOException, ParseException {
        return new Configuration();
    }

    /**
     * Returns an immutable list of predefined configurations for this Java Virtual Machine (JVM).
     *
     * @return the list of predefined configurations, not {@code null}
     */
    public static List<Configuration> getConfigurations() {
        return Collections.emptyList();
    }
}
