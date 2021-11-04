package org.gradle.jfr.example

import spock.lang.Specification


class JfrPolyfillSpec extends Specification {

    def "check if jfr-polyfill is active as expected"() {
        expect:
        JfrInfo.polyfillActive == JfrInfo.jfrPolyfillExpected
    }
}
