package com.teseus.inaction.ch8;

public class ProcessTheAnswerInvokeLambda {
    public static void main(String[] args) {
        ProcessTheAnswer.processTheAnswer(it->it*2);

        return;
    }
}
