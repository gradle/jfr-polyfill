package org.gradle.jfr.example

import spock.lang.Specification
import spock.util.environment.Jvm


class FlightRecorderSpec extends Specification {

    def flightRecorder = new FlightRecorderUser()

    def "nonNullReturnValues"(){
        when:
        flightRecorder.nonNullReturnValues()

        then:
        noExceptionThrown()
    }

    def "checkListeners"(){
        when:
        flightRecorder.checkListeners()

        then:
        noExceptionThrown()
    }

    def "checkPeriodicEvent"(){
        when:
        flightRecorder.checkPeriodicEvent()

        then:
        noExceptionThrown()
    }

    def "checkEventRegistration"(){
        when:
        flightRecorder.checkEventRegistration()

        then:
        noExceptionThrown()
    }


    def "check if jfr is available as expected"() {
        expect:
        flightRecorder.jfrAvailable != Jvm.current.isJava9() || Jvm.current.isJava10()
    }

}
