/*
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
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

package jdk.jfr.internal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.AccessControlContext;
import java.util.Objects;
import java.util.Set;

// User must never be able to subclass directly.
//
// Never put Control or Setting Control in a collections
// so overridable versions of hashCode or equals are
// executed in the wrong context. TODO: wrap this class
// in SsecureControl directly when it is instantiated and
// forward calls using AccessControlContext
abstract public class Control {

    // called by exposed subclass in external API
    public Control(AccessControlContext acc) {
    }

    // For user code to override, must never be called from jdk.jfr.internal
    // for user defined settings
    public abstract String combine(Set<String> values);

    // For user code to override, must never be called from jdk.jfr.internal
    // for user defined settings
    public abstract void setValue(String value);

    // For user code to override, must never be called from jdk.jfr.internal
    // for user defined settings
    public abstract String getValue();

    // Precaution to prevent a malicious user from instantiating instances
    // of a control where the context has not been set up.
    @Override
    public final Object clone() throws java.lang.CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
