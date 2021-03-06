package com.teseus.thirtyminutecoding;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

@Slf4j
public class FluxGenerator {

    public static void main(String[] args) {
        assertArrayEquals(new Long[]{0L,1L,1L,2L,3L},getFibonaci().toArray());
    }

    private static List<Long> getFibonaci() {
        List<Long> temp = new ArrayList();

        final Flux<Object> fibonacci = Flux.generate(
                () -> Tuples.of(0L, 1L),
                (state, sink) -> {
                    sink.next(state.getT1());
                    log.debug("generated " + state.getT1());
                    return Tuples.of(state.getT2(), state.getT1() + state.getT2());
                }
        );

        fibonacci.take(5)
        .subscribe(
                it->{
                    log.debug("consuming " + it);
                    temp.add((Long)it);
                }
        );

        return temp;
    }
}
