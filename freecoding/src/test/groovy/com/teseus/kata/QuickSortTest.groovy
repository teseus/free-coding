package com.teseus.kata

import spock.lang.Specification

class QuickSortTest extends Specification {

    def testSort1() {
        given:
        def integers = [1,5,2,3,4,6] as int[]
        when:
        def sorted = QuickSort.sort(integers)
        then:
        sorted == [1,2,3,4,5,6] as int[]
    }

    def testSort2() {
        given:
        def integers = [20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0] as int[]
        when:
        def sorted = QuickSort.sort(integers)
        then:
        sorted == [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20] as int[]
    }

    def testSort3() {
        given:
        def integers = [20,14,13,12,11,10,9,19,18,17,16,15,8,7,6,5,4,3,2,1,0] as int[]
        when:
        def sorted = QuickSort.sort(integers)
        then:
        sorted == [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20] as int[]
    }

}
