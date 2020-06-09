package com.teseus.inaction.ch8;

import kotlin.jvm.functions.Function1;

public class ProcessTheAnswerInvoke {
    public static void main(String[] args) {
        ProcessTheAnswer.processTheAnswer(new Function1<Integer, Integer>() {
            @Override
            public Integer invoke(Integer number) {
                return number*2;
            }
        });

        return;
    }
}
