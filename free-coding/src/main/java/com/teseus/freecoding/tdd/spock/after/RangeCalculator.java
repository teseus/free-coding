package com.teseus.freecoding.tdd.spock.after;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RangeCalculator {
    private RatioRepository ratioRepository;

    public int func1(int arg1){
        arg1 *= Math.max(1, arg1/10.0d * ratioRepository.getOne(1L).getValue());
        return arg1;
    }
}
