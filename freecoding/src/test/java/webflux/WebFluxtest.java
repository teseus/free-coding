package webflux;

import learning.rxJava.MyUtils;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class WebFluxtest {
    @Test
    public void simple_test(){
        Flux.interval(Duration.ofSeconds(1))
                .map(String::valueOf)
                .subscribe(System.out::println);

        MyUtils.sleep(5000);
    }
}
