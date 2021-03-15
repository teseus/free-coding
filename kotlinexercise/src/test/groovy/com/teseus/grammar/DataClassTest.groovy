package com.teseus.grammar

import spock.lang.Specification

class DataClassTest extends Specification {
    def "must make a data class"() {
        when:
        def data = new DataClass("myname", "good model")
        then:
        data != null
    }
}
