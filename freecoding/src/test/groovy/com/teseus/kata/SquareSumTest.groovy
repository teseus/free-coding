package com.teseus.kata

import spock.lang.Specification

class SquareSumTest extends Specification {

    def summary(){
        when:
        def sum = SquareSum.squareSum(INPUT)

        then:
        9 == sum

        where:
        INPUT
        [1,2,2]
    }

}
