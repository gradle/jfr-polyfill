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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class for accessing, controlling, and managing Flight Recorder.
 * <p>
 * This class provides the methods necessary for creating, starting, stopping,
 * and destroying recordings.
 *
 * @since 8
 */
public final class FlightRecorder {
    private static final FlightRecorder platformRecorder = new FlightRecorder();

    private FlightRecorder() {

    }

    /**
     * Returns an immutable list of the available recordings.
     * <p>
     * A recording becomes available when it is created. It becomes unavailable when it
     * is in the {@code CLOSED} state, typically after a call to
     * {@link Recording#close()}.
     *
     * @return a list of recordings, not {@code null}
     */
    public List<Recording> getRecordings() {
        return Collections.emptyList();
    }

    /**
     * Creates a snapshot of all available recorded data.
     * <p>
     * A snapshot is a synthesized recording in a {@code STOPPPED} state. If no data is
     * available, a recording with size {@code 0} is returned.
     * <p>
     * A snapshot provides stable access to data for later operations (for example,
     * operations to change the interval or to reduce the data size).
     * <p>
     * The following example shows how to create a snapshot and write a subset of the data to a file.
     *
     * <pre>
     * <code>
     * try (Recording snapshot = FlightRecorder.getFlightRecorder().takeSnapshot()) {
     *   if (snapshot.getSize() &gt; 0) {
     *     snapshot.setMaxSize(100_000_000);
     *     snapshot.setMaxAge(Duration.ofMinutes(5));
     *     snapshot.dump(Paths.get("snapshot.jfr"));
     *   }
     * }
     * </code>
     * </pre>
     *
     * The caller must close the recording when access to the data is no longer
     * needed.
     *
     * @return a snapshot of all available recording data, not {@code null}
     */
    public Recording takeSnapshot() {
        Recording snapshot = new Recording();
        snapshot.setName("Snapshot");
        return snapshot;
    }

    /**
     * Registers an event class.
     * <p>
     * If the event class is already registered, then the invocation of this method is
     * ignored.
     *
     * @param eventClass the event class to register, not {@code null}
     *
     * @throws IllegalArgumentException if class is abstract or not a subclass
     *         of {@link Event}
     * @throws SecurityException if a security manager exists and the caller
     *         does not have {@code FlightRecorderPermission("registerEvent")}
     */
    public static void register(Class<? extends Event> eventClass) {
        Objects.requireNonNull(eventClass);
    }

    /**
     * Unregisters an event class.
     * <p>
     * If the event class is not registered, then the invocation of this method is
     * ignored.
     *
     * @param eventClass the event class to unregistered, not {@code null}
     * @throws IllegalArgumentException if a class is abstract or not a subclass
     *         of {@link Event}
     *
     * @throws SecurityException if a security manager exists and the caller
     *         does not have {@code FlightRecorderPermission("registerEvent")}
     */
    public static void unregister(Class<? extends Event> eventClass) {
        Objects.requireNonNull(eventClass);
    }

    /**
     * Returns the Flight Recorder for the platform.
     *
     * @return a Flight Recorder instance, not {@code null}
     *
     * @throws IllegalStateException if Flight Recorder can't be created (for
     *         example, if the Java Virtual Machine (JVM) lacks Flight Recorder
     *         support, or if the file repository can't be created or accessed)
     *
     * @throws SecurityException if a security manager exists and the caller does
     *         not have {@code FlightRecorderPermission("accessFlightRecorder")}
     */
    public static FlightRecorder getFlightRecorder() throws IllegalStateException, SecurityException {
        return platformRecorder;
    }

    /**
     * Adds a hook for a periodic event.
     * <p>
     * The implementation of the hook should return as soon as possible, to
     * avoid blocking other Flight Recorder operations. The hook should emit
     * one or more events of the specified type. When a hook is added, the
     * interval at which the call is invoked is configurable using the
     * {@code "period"} setting.
     *
     * @param eventClass the class that the hook should run for, not {@code null}
     * @param hook the hook, not {@code null}
     * @throws IllegalArgumentException if a class is not a subclass of
     *         {@link Event}, is abstract, or the hook is already added
     * @throws IllegalStateException if the event class has the
     *         {@code Registered(false)} annotation and is not registered manually
     * @throws SecurityException if a security manager exists and the caller
     *         does not have {@code FlightRecorderPermission("registerEvent")}
     */
    public static void addPeriodicEvent(Class<? extends Event> eventClass, Runnable hook) throws SecurityException {
        Objects.requireNonNull(eventClass);
        Objects.requireNonNull(hook);
    }

    /**
     * Removes a hook for a periodic event.
     *
     * @param hook the hook to remove, not {@code null}
     * @return {@code true} if hook is removed, {@code false} otherwise
     * @throws SecurityException if a security manager exists and the caller
     *         does not have {@code FlightRecorderPermission("registerEvent")}
     */
    public static boolean removePeriodicEvent(Runnable hook) throws SecurityException {
        Objects.requireNonNull(hook);
        return false;
    }

    /**
     * Returns an immutable list that contains all currently registered events.
     * <p>
     * By default, events are registered when they are first used, typically
     * when an event object is allocated. To ensure an event is visible early,
     * registration can be triggered by invoking the
     * {@link FlightRecorder#register(Class)} method.
     *
     * @return list of events, not {@code null}
     */
    public List<EventType> getEventTypes() {
        return Collections.emptyList();
    }

    /**
     * Adds a recorder listener and captures the {@code AccessControlContext} to
     * use when invoking the listener.
     * <p>
     * If Flight Recorder is already initialized when the listener is added, then the method
     * {@link FlightRecorderListener#recorderInitialized(FlightRecorder)} method is
     * invoked before returning from this method.
     *
     * @param changeListener the listener to add, not {@code null}
     *
     * @throws SecurityException if a security manager exists and the caller
     *         does not have
     *         {@code FlightRecorderPermission("accessFlightRecorder")}
     */
    public static void addListener(FlightRecorderListener changeListener) {
        Objects.requireNonNull(changeListener);
    }

    /**
     * Removes a recorder listener.
     * <p>
     * If the same listener is added multiple times, only one instance is
     * removed.
     *
     * @param changeListener listener to remove, not {@code null}
     *
     * @throws SecurityException if a security manager exists and the caller
     *         does not have
     *         {@code FlightRecorderPermission("accessFlightRecorder")}
     *
     * @return {@code true}, if the listener could be removed, {@code false}
     *         otherwise
     */
    public static boolean removeListener(FlightRecorderListener changeListener) {
        Objects.requireNonNull(changeListener);
        return false;
    }

    /**
     * Returns {@code true} if the Java Virtual Machine (JVM) has Flight Recorder capabilities.
     * <p>
     * This method can quickly check whether Flight Recorder can be
     * initialized, without actually doing the initialization work. The value may
     * change during runtime and it is not safe to cache it.
     *
     * @return {@code true}, if Flight Recorder is available, {@code false}
     *         otherwise
     *
     * @see FlightRecorderListener for callback when Flight Recorder is
     *      initialized
     */
    public static boolean isAvailable() {
        return false;
    }
}
