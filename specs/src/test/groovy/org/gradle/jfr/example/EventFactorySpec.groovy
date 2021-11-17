/*
 * Copyright (c) 2021, Gradle and/or its affiliates. All rights reserved.
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
 * Please contact Gradle Inc., Gradle Inc., 2261 Market St #4081,
 * San FranciscoCA 94114, United States
 * or visit www.gradle.org if you need additional information or have any
 * questions.
 */

package org.gradle.jfr.example

import spock.lang.Specification


class EventFactorySpec extends Specification {

    def eventFactory = new EventFactoryUser()

    def "custom events can be defined"() {
        when:
        def factory = eventFactory.createEventFactory()

        then:

        if (JfrInfo.jfrPolyfillExpected) {
            with(factory.eventType) {
                name == 'jfr.polyfill.DummyEvent'
                label == null
                description == null
                getField('message').name == 'dummyName'
                getField('number').name == 'dummyName'
            }
        } else {
            with(factory.eventType) {
                name == 'com.example.HelloWorld'
                label == 'Hello World'
                description == 'Helps programmer getting started'
                getField('message').name == 'message'
                getField('number').name == 'number'
            }
        }

        when:
        def event = eventFactory.createDynamicEvent(factory)

        then:
        if (JfrInfo.jfrPolyfillExpected) {
            assert event.getClass().name == 'jdk.jfr.EventFactory$DummyEvent'
            assert !event.enabled
        } else {
            assert event.getClass().name != 'jdk.jfr.EventFactory$DummyEvent'
        }
    }
}
