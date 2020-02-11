package feecoding.exercise.kata

import freecoding.exercise.kata.NthSeries
import spock.lang.Specification

class NthSeriesTest extends Specification {
    def test0() {
        expect:
        "1" == NthSeries.seriesSum(1)
    }

    def test1() {
        expect:
        "1.57" == NthSeries.seriesSum(5)
    }
    def test2() {
        expect:
        assert "1.77" == NthSeries.seriesSum(9)
    }
    def test3() {
        expect:
        assert "1.94" == NthSeries.seriesSum(15)
    }
}