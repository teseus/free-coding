package com.teseus.exercise

import spock.lang.Specification
import spock.lang.Unroll

import java.util.function.Function

class StateCommanderTest extends Specification {

    @Unroll
    def "test registerFunction"() {
        given:
        Function<String, Integer> func = { it -> OUTVAL }

        when:
        StateCommander.registerFunction(CurrentMode.WAIT_FOR_SIZE, func)
        StateCommander.swith(CurrentMode.WAIT_FOR_SIZE)
        def returnVal = StateCommander.execute(INVAL)

        then:
        noExceptionThrown()
        returnVal == OUTVAL

        where:
        INVAL || OUTVAL
        100   || 110

    }
}
