package com.teseus.exercise

import spock.lang.Specification

class MeanMediaModeTest extends Specification {
    def "Average Test"() {
        when:
        def value = MeanMediaMode.getAverageVal(1, 2, 3, 4, 5)
        then:
        value == 3f
    }

    def "Mean Test" () {
        when:
        def value = MeanMediaMode.getMeanVal(1,2,3,4,5,6,7,8,9)
        then:
        value == 5
        when:
        value = MeanMediaMode.getMeanVal(1,2,3,4,5,6)
        then:
        value == 4
    }
}
