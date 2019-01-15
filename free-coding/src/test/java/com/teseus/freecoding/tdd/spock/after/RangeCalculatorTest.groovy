package com.teseus.freecoding.tdd.spock.after


import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class RangeCalculatorTest extends Specification {
    @Subject
    RangeCalculator rangeCalculator

    RatioRepository ratioRepositoryMock = Mock(RatioRepository)

    def setup() {

    }

    @Unroll
    def "test1 #DESC"() {
        given:
        rangeCalculator = new RangeCalculator(ratioRepository: ratioRepositoryMock)
        when:
        def result = rangeCalculator.func1(INPUT)
        then:
        result == OUTPUT
        and:
        1 * ratioRepositoryMock.getOne(_) >> Mock(Ratio){
            getValue() >> 2.0d
        }

        where:
        DESC         | INPUT || OUTPUT
        "UNDER TEST" | 1     || 1
        "OVER TEST1" | 5     || 5
        "OVER TEST2" | 10    || 20
        "OVER TEST3" | 20    || 80
    }
}
