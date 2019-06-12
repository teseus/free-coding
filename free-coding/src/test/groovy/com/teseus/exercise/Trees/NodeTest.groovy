package com.teseus.exercise.Trees

import spock.lang.Specification

class NodeTest extends Specification {
    def "test insert"() {
        when:
        def root = Node.insert(null, 1)
        then:
        root.data == 1
        root.left == null
        root.right == null

        when:
        def returned = Node.insert(root, 2)
        then:
        root == returned
        root.right.data == 2

        when:
        def returned2 = Node.insert(root, 3)
        then:
        root == returned2
        root.right.right.data == 3

    }
}
x