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
import java.util.Map;
import java.util.Objects;

/**
 * Describes event metadata, such as labels, descriptions and units.
 * <p>
 * The following example shows how {@code AnnotationElement} can be used to dynamically define events.
 *
 * <pre>
 * <code>
 *   List{@literal <}AnnotationElement{@literal >} typeAnnotations = new ArrayList{@literal <}{@literal >}();
 *   typeannotations.add(new AnnotationElement(Name.class, "com.example.HelloWorld");
 *   typeAnnotations.add(new AnnotationElement(Label.class, "Hello World"));
 *   typeAnnotations.add(new AnnotationElement(Description.class, "Helps programmer getting started"));
 *
 *   List{@literal <}AnnotationElement{@literal >} fieldAnnotations = new ArrayList{@literal <}{@literal >}();
 *   fieldAnnotations.add(new AnnotationElement(Label.class, "Message"));
 *
 *   List{@literal <}ValueDescriptor{@literal >} fields = new ArrayList{@literal <}{@literal >}();
 *   fields.add(new ValueDescriptor(String.class, "message", fieldAnnotations));
 *
 *   EventFactory f = EventFactory.create(typeAnnotations, fields);
 *   Event event = f.newEvent();
 *   event.commit();
 * </code>
 * </pre>
 *
 * @since 8
 */
public final class AnnotationElement {

    /**
     * Creates an annotation element to use for dynamically defined events.
     * <p>
     * Supported value types are {@code byte}, {@code int}, {@code short},
     * {@code long}, {@code double}, {@code float}, {@code boolean}, {@code char},
     * and {@code String}. Enums, arrays and classes, are not supported.
     * <p>
     * If {@code annotationType} has annotations (directly present, indirectly
     * present, or associated), then those annotation are recursively included.
     * However, both the {@code annotationType} and any annotation found recursively
     * must have the {@link MetadataDefinition} annotation.
     * <p>
     * To statically define events, see {@link Event} class.
     *
     * @param annotationType interface extending
     *        {@code java.lang.annotation.Annotation}, not {@code null}
     * @param values a {@code Map} with keys that match method names of the specified
     *        annotation interface
     * @throws IllegalArgumentException if value/key is {@code null}, an unsupported
     *         value type is used, or a value/key is used that doesn't match the
     *         signatures in the {@code annotationType}
     */
    public AnnotationElement(Class<? extends Annotation> annotationType, Map<String, Object> values) {
        Objects.requireNonNull(annotationType);
        Objects.requireNonNull(values);
    }

    /**
     * Creates an annotation element to use for dynamically defined events.
     * <p>
     * Supported value types are {@code byte}, {@code int}, {@code short},
     * {@code long}, {@code double}, {@code float}, {@code boolean}, {@code char},
     * and {@code String}. Enums, arrays, and classes are not supported.
     * <p>
     * If {@code annotationType} has annotations (directly present, indirectly
     * present, or associated), then those annotations are recursively included.
     * However, both {@code annotationType} and any annotation found recursively
     * must have the {@link MetadataDefinition} annotation.
     * <p>
     * To statically define events, see {@link Event} class.
     *
     * @param annotationType interface extending
     *        {@code java.lang.annotation.Annotation,} not {@code null}
     * @param value the value that matches the {@code value} method of the specified
     *        {@code annotationType}
     * @throws IllegalArgumentException if value/key is {@code null}, an unsupported
     *         value type is used, or a value/key is used that doesn't match the
     *         signatures in the {@code annotationType}
     */
    public AnnotationElement(Class<? extends Annotation> annotationType, Object value) {
    }

    /**
     * Creates an annotation element to use for dynamically defined events.
     * <p>
     * Supported value types are {@code byte}, {@code short}, {@code int},
     * {@code long}, {@code double}, {@code float}, {@code boolean}, {@code char},
     * and {@code String}. Enums, arrays, and classes are not supported.
     * <p>
     * If {@code annotationType} has annotations (directly present, indirectly
     * present or associated), then those annotation are recursively included.
     * However, both {@code annotationType} and any annotation found recursively
     * must have the {@link MetadataDefinition} annotation.
     * <p>
     * To statically define events, see {@link Event} class.
     *
     * @param annotationType interface extending java.lang.annotation.Annotation,
     *        not {@code null}
     */
    public AnnotationElement(Class<? extends Annotation> annotationType) {
    }

    /**
     * Returns an immutable list of annotation values in an order that matches the
     * value descriptors for this {@code AnnotationElement}.
     *
     * @return list of values, not {@code null}
     */
    public List<Object> getValues() {
        return Collections.emptyList();
    }

    /**
     * Returns an immutable list of descriptors that describes the annotation values
     * for this {@code AnnotationElement}.
     *
     * @return the list of value descriptors for this {@code Annotation}, not
     *         {@code null}
     */
    public List<ValueDescriptor> getValueDescriptors() {
        return Collections.emptyList();
    }

    /**
     * Returns an immutable list of annotation elements for this
     * {@code AnnotationElement}.
     *
     * @return a list of meta annotation, not {@code null}
     */
    public List<AnnotationElement> getAnnotationElements() {
        return Collections.emptyList();
    }

    /**
     * Returns the fully qualified name of the annotation type that corresponds to
     * this {@code AnnotationElement} (for example, {@code "jdk.jfr.Label"}).
     *
     * @return type name, not {@code null}
     */
    public String getTypeName() {
        return "jfr.polyfill.DummyAnnotation";
    }

    /**
     * Returns a value for this {@code AnnotationElement}.
     *
     * @param name the name of the method in the annotation interface, not
     *        {@code null}.
     *
     * @return the annotation value, not {@code null}.
     *
     * @throws IllegalArgumentException if a method with the specified name does
     *         not exist in the annotation
     */
    public Object getValue(String name) {
        Objects.requireNonNull(name);
        return "";
    }

    /**
     * Returns {@code true} if an annotation value with the specified name exists in
     * this {@code AnnotationElement}.
     *
     * @param name name of the method in the annotation interface to find, not
     *        {@code null}
     *
     * @return {@code true} if method exists, {@code false} otherwise
     */
    public boolean hasValue(String name) {
        return false;
    }

    /**
     * Returns the first annotation for the specified type if an
     * {@code AnnotationElement} with the same name exists, else {@code null}.
     *
     * @param <A> the type of the annotation to query for and return if it exists
     * @param annotationType the {@code Class object} corresponding to the annotation type,
     *        not {@code null}
     * @return this element's annotation for the specified annotation type if
     *         it it exists, else {@code null}
     */
    public final <A> A getAnnotation(Class<? extends Annotation> annotationType) {
        Objects.requireNonNull(annotationType);
        return null;
    }

    /**
     * Returns the type ID for this {@code AnnotationElement}.
     * <p>
     * The ID is a unique identifier for the type in the Java Virtual Machine (JVM). The ID might not
     * be the same between JVM instances.
     *
     * @return the type ID, not negative
     */
    public long getTypeId() {
        return 42;
    }
}
