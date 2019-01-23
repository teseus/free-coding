package com.teseus.freecoding.tdd.spock.after;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RangeCalculator {
    private final RangeEntityRepository rangeEntityRepository;
    private final RestTemplate restTemplate;

    public int calc(int arg) {
        if (arg < 10) {
            return arg;
        }
        if (arg <50) {
            return arg * 2;
        }
        RangeEntity one = rangeEntityRepository.getOne(1L);
        RangeEntity ret = restTemplate.getForObject("http://aaa", RangeEntity.class);
        return arg * one.getValue();
    }
}
