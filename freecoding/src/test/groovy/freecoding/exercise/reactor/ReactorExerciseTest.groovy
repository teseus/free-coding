package freecoding.exercise.reactor

import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

import java.util.function.Consumer

class ReactorExerciseTest extends Specification {
    def "verify"(){
        when:
        def mono = Mono.just("Hello").log()
        then:
        StepVerifier.create(mono)
        .expectNext("Hello")
        .expectComplete()
    }

    def "verify error"() {
        when:
        def mono = Mono.just("hello")
                .map({ it -> throw new RuntimeException("runtime exception") })
                .log()

        println "-------------------------"
        mono.subscribe({it-> println(it)}, {ex-> println ex})
        mono.subscribe({it-> println(it)}, {Throwable.&printStackTrace as Consumer})
        println "========================="
        then:
        StepVerifier.create(mono)
        .expectError(RuntimeException.class)
        .verify()
    }
}
