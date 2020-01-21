package com.teseus.grammar

import org.codehaus.groovy.runtime.metaclass.MissingMethodExceptionNoStack
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject


class DrinkTest extends Specification {
    @Subject
    Drink drink

    @Shared
    ByteArrayOutputStream buffer

    def setup(){
        buffer = new ByteArrayOutputStream()
        System.out = new PrintStream(buffer)
    }

    def "test swallow"() {
        given:
        drink = new Drink()
        when:
        drink.swallow()
        then:
        noExceptionThrown()
        "음료를 마십니다.\n" == buffer.toString()
    }

    def "inherited Cola should give other message"(){
        given:
        Drink cola = new Cola()
        when:
        cola.swallow()
        then:
        "음료 콜라를 마십니다.\n" == buffer.toString()
        buffer.reset()
        when:
        cola.say()
        then:
        noExceptionThrown()
        "코카콜라 맛있어.\n" == buffer.toString()
    }
}
