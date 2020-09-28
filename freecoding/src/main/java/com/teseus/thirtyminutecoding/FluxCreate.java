package com.teseus.thirtyminutecoding;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertArrayEquals;

@Slf4j
public class FluxCreate {
    public static void main(String[] args) {
        assertArrayEquals(
                new Long[]{0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L},
                FluxCreate.getFibonacci().toArray());
    }

    private static List<Long> getFibonacci() {
        Flux<Long> fibonacci = Flux.create(e -> {
            long current = 1, prev = 0;
            AtomicBoolean stop = new AtomicBoolean(false);
            e.onDispose(() -> {
                stop.set(true);
                log.debug("stop recieved.");
            });
            while (current > 0) {
                log.debug("created " + prev);
                e.next(prev);
                long next = current + prev;
                prev = current;
                current = next;
            }
            log.debug("completed due to over long max value");
            e.complete();
        });

        List<Long> temp = new ArrayList<>();

        fibonacci.take(10).subscribe(e -> {
            temp.add(e);
            log.debug("consumed " + e);
        });
        return temp;
    }
}
