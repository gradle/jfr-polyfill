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

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Describes the event fields and annotation elements.
 *
 * @since 8
 */
public final class ValueDescriptor {

    /**
     * <p>
     * Constructs a value descriptor, useful for dynamically creating event types and
     * annotations.
     * <P>
     * The following types are supported:
     * <ul>
     * <li>{@code byte.class}
     * <li>{@code short.class}
     * <li>{@code int.class}
     * <li>{@code long.class}
     * <li>{@code char.class}
     * <li>{@code float.class}
     * <li>{@code double.class}
     * <li>{@code boolean.class}
     * <li>{@code String.class}
     * <li>{@code Class.class}
     * <li>{@code Thread.class}
     * </ul>
     *
     * <p>
     * The name must be a valid Java identifier (for example, {@code "maxThroughput"}). See 3.8
     * Java Language Specification for more information.
     *
     * @param type the type, not {@code null}
     * @param name the name, not {@code null}
     *
     * @throws SecurityException if a security manager is present and the caller
     *         doesn't have {@code FlightRecorderPermission("registerEvent")}
     *
     */
    public ValueDescriptor(Class<?> type, String name) {
    }

    /**
     * <p>
     * Constructs a value descriptor, useful for dynamically creating event types and
     * annotations.
     * <P>
     * The following types are supported:
     * <ul>
     * <li>{@code byte.class}
     * <li>{@code short.class}
     * <li>{@code int.class}
     * <li>{@code long.class}
     * <li>{@code char.class}
     * <li>{@code float.class}
     * <li>{@code double.class}
     * <li>{@code boolean.class}
     * <li>{@code String.class}
     * <li>{@code Class.class}
     * <li>{@code Thread.class}
     * </ul>
     *
     * <p>
     * The name must be a valid Java identifier (for example, {@code "maxThroughput"}). See 3.8
     * Java Language Specification for more information.
     *
     * @param type the type, not {@code null}
     * @param name the name, not {@code null}
     * @param annotations the annotations on the value descriptors, not
     *        {@code null}
     *
     * @throws SecurityException if a security manager is present and the caller
     *         doesn't have {@code FlightRecorderPermission("registerEvent")}
     */
    public ValueDescriptor(Class<?> type, String name, List<AnnotationElement> annotations) {
        Objects.requireNonNull(annotations);
    }

    /**
     * Returns a human-readable name that describes the value (for example,
     * {@code "Maximum Throughput"}).
     *
     * @return a human-readable name, or {@code null} if doesn't exist
     */
    public String getLabel() {
        return "dummyLabel";
    }

    /**
     * Returns the name of the value (for example, {@code "maxThroughput"}).
     *
     * @return the name, not {@code null}
     */
    public String getName() {
        return "dummyName";
    }

    /**
     * Returns a sentence describing the value (for example, {@code "Maximum
     * throughput in the transaction system. Value is reset after each new
     * batch."}).
     *
     * @return the description, or {@code null} if doesn't exist
     */
    public String getDescription() {
        return "dummyDescription";
    }

    /**
     * Returns a textual identifier that specifies how a value represented by
     * this {@link ValueDescriptor} is interpreted or formatted.
     * <p>
     * For example, if the value descriptor's type is {@code float} and the
     * event value is {@code 0.5f}, a content type of
     * {@code "jdk.jfr.Percentage"} hints to a client that the value is a
     * percentage and that it should be rendered as {@code "50%"}.
     * <p>
     * The JDK provides the following predefined content types:
     * <ul>
     * <li>jdk.jfr.Percentage</li>
     * <li>jdk.jfr.Timespan</li>
     * <li>jdk.jfr.Timestamp</li>
     * <li>jdk.jfr.Frequency</li>
     * <li>jdk.jfr.Flag</li>
     * <li>jdk.jfr.MemoryAddress</li>
     * <li>jdk.jfr.DataAmount</li>
     * <li>jdk.jfr.NetworkAddress</li>
     * </ul>
     * <p>
     * User-defined content types can be created by using the {@link ContentType} class.
     *
     * @return the content type, or {@code null} if doesn't exist
     *
     * @see ContentType
     */
    public String getContentType() {
        return null;
    }

    /**
     * Returns the fully qualified class name of the type that is associated with
     * this value descriptor.
     *
     * @return the type name, not {@code null}
     *
     * @see ValueDescriptor#getTypeId()
     */
    public String getTypeName() {
        return "jfr.polyfill.DummyType";
    }

    /**
     * Returns a unique ID for the type in the Java virtual Machine (JVM).
     *
     * The ID might not be the same between JVM instances.
     *
     * @return the type ID, not negative
     */
    public long getTypeId() {
        return 42;
    }

    /**
     * Returns if this value descriptor is an array type.
     *
     * @return {@code true} if it is an array type, {@code false} otherwise
     */
    public boolean isArray() {
        return false;
    }

    /**
     * Returns the first annotation for the specified type if an annotation
     * element with the same name is directly present for this value descriptor,
     * {@code null} otherwise.
     *
     * @param <A> the type of the annotation to query for and return if present
     * @param annotationType the Class object that corresponds to the annotation
     *        type, not {@code null}
     * @return this element's annotation for the specified annotation type if
     *         directly present, else {@code null}
     */
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        Objects.requireNonNull(annotationType);
        return null;
    }

    /**
     * Returns an immutable list of annotation elements for this value
     * descriptor.
     *
     * @return a list of annotations, not {@code null}
     */
    public List<AnnotationElement> getAnnotationElements() {
        return Collections.emptyList();
    }

    /**
     * Returns an immutable list of value descriptors if the type is complex,
     * else an empty list.
     *
     * @return a list of value descriptors, not {@code null}
     */
    public List<ValueDescriptor> getFields() {
        return Collections.emptyList();
    }
}
