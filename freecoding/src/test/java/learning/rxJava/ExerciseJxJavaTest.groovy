package learning.rxJava

import spock.lang.Specification

class ExerciseJxJavaTest extends Specification {

    def "an integer can be incremented"() {
        given:
        int i = 1

        when:
        i++

        then:
        i == 2
    }

}