package com.teseus.freecoding

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class RangeCalculatorTest extends Specification {
    @Subject
    RangeCalculator rangeCalculator

    def setup() {

    }

    @Unroll
    def "test1 #DESC"() {
        given:
        rangeCalculator = new RangeCalculator()
        when:
        def result = rangeCalculator.func1(INPUT)
        then:
        result == OUTPUT
        where:
        DESC         | INPUT || OUTPUT
        "UNDER TEST" | 1     || 1
        "OVER TEST1" | 5     || 5
        "OVER TEST2" | 10    || 20
        "OVER TEST3" | 20    || 80
    }
}
