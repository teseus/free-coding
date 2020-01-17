package com.teseus.kotlin.webflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom

@SpringBootApplication
class WebfluxApplication

fun main(args: Array<String>) {
    runApplication<WebfluxApplication>(*args)
}

@RestController
class ApiController{
    @GetMapping(value = ["/stock/{symbol}"],
            produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getStockPrice(@PathVariable symbol: String): Flux<StockPrice> {
        return Flux.interval(Duration.ofSeconds(1))
                .map{StockPrice(symbol, getRandomPrice(), LocalDateTime.now())}
    }

    private fun getRandomPrice(): Double {
        return ThreadLocalRandom.current().nextDouble(100.0)
    }
}

data class StockPrice (val symbol:String, val price: Double, val date: LocalDateTime)
