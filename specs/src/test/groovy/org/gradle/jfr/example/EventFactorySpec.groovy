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
