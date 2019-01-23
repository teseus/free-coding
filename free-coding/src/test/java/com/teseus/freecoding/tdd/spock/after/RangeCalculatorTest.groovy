package com.teseus.freecoding.tdd.spock.after


import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class RangeCalculatorTest extends Specification {
    @Subject
    RangeCalculator rangeCalculator
    RangeEntityRepository rangeEntityRepositoryMock
    RestTemplate restTemplateStub

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    def "#DESC input #INPUT output #OUTPUT"() {
        given:
        rangeEntityRepositoryMock = Mock(RangeEntityRepository)
        restTemplateStub = Stub(RestTemplate){
            getForObject(*_) >> RangeEntity.create(1, 10)
        }
        rangeCalculator = new RangeCalculator(rangeEntityRepositoryMock, restTemplateStub)
        when:
        def ret = rangeCalculator.calc(INPUT)
        then:
        noExceptionThrown()
        and:
        ret == OUTPUT
        and:
        CALLTIME * rangeEntityRepositoryMock.getOne(_) >>
                RangeEntity.create(1, 10)
        where:
        DESC      | INPUT | OUTPUT | CALLTIME
        "under10" | 5     | 5      | 0
        "over10"  | 10    | 20     | 0
        "over50"  | 50    | 500    | 1
    }
}
